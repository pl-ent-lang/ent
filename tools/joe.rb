#!/usr/bin/env ruby

require 'optparse'
require "nokogiri"

POLYGLOT_OPTIONS  = File.expand_path("../polyglot_options.xml", __FILE__)
JAVA_OPTIONS  = File.expand_path("../java_options.xml", __FILE__)

class Option
  attr_reader :name, :raw, :pattern

  def initialize(name, raw, pattern)
    @name = name
    @raw = raw
    @pattern = pattern
  end

  def match?(string)
    @pattern.match(string)
  end

  def expects?
    return @name != @raw
  end
end

class OptionDatabase

  def initialize(file)
    xml = Nokogiri::XML(File.open(file))
    @options = {}

    options = xml.xpath("//option")
    options.each do |o|
      pattern = Regexp.new("-#{o["pattern"]}?(\s|$)")
      @options[o["name"]] = Option.new(o["name"], o["pattern"], pattern)
    end
  end

  def each 
    @options.each { |k,v| yield(k,v) }
  end

  def hasOption?(name)
    @options.key?(name)
  end

  def getOption(name)
    @options[name]
  end

end

class Joe 

  def initialize(polyglot_options, java_options, languages) 
    @polyglot_database = OptionDatabase.new(polyglot_options)
    @java_database = OptionDatabase.new(java_options)
    @languages = language_regex(languages)
  end

  def language_regex(languages) 
    language_regex = "("
    languages.each do |l|
      language_regex += "[^\s]*\\.#{l}|"
    end
    language_regex += "@.*)"
    Regexp.new(language_regex)
  end

  def flatten(options)
    lines = File.read(options[1..-1]).split("\n")
    files = []
    string = ""

    i = 0
    while i < lines.length
      l = lines[i]
      if l.start_with?("-") 
        opt = @java_database.getOption(l[1..-1])
        if opt != nil && opt.expects?
          string += l + " " + lines[i+1] + " "
          i = i + 1
        else
          string += l + " "
        end
      else
        files << l
      end
      i = i + 1
    end 

    files.each do |f|
      string += f + " "
    end
  
    return string
  end

  def erase(options) 
    polyglot_options = []
    java_options = []
    @java_database.each do |name, option|
      matched = option.match?(options)
      next unless matched

      if @polyglot_database.hasOption?(name)
        polyglot_options << matched[0].rstrip
      else
        java_options << matched[0].rstrip
      end
    end

    @polyglot_database.each do |name, option|
      matched = option.match?(options)
      next unless matched

      unless @java_database.hasOption?(name)
        polyglot_options << matched[0].rstrip
      end
    end

    # Special case for postopts
    postopts = /postopts\s"(.*)"/.match(options)
    if (postopts != nil)
      java_options << postopts[1]
    end

    files = options.scan(@languages)
    if java_options.count > 0
      polyglot_options << "-postopts \"" + java_options.join(" ") + "\""
    end
    polyglot_options << files
    polyglot_options.join(" ")
  end

end


def argsplit(allargs) 
  ind = allargs.index("--")
  return allargs[0..ind-1], allargs[ind+1..allargs.length]
end

if __FILE__ == $0
  options = {}
  options[:languages] = ["java"]

  optparse = OptionParser.new do |opts|
    opts.banner = "Usage: joe.rb [options] [javac options string]"

    opts.on("-p", 
            "=MANDATORY",
            "Supply an XML file describing valid polyglot options. 
            Defaults to \"polyglot_options.xml\"") do |p|
      options[:polyglot_options] = p
    end

    opts.on("-j", 
            "=MANDATORY",
            "Supply an XML file describing valid java options. 
            Defaults to \"java.xml\"") do |j|
      options[:java_options] = j
    end

    opts.on("-l", 
            "=MANDATORY",
            "Add a valid language extention that can be compiled by 
            the polyglot compiler. .java enabled by default.") do |l|
      options[:languages] << l
    end

  end

  opts, args = argsplit(ARGV)

  option_string = args.join(" ")

  optparse.parse!

  unless option_string
    puts optparse.help
    exit 1
  end

  polyglot_options = 
    options.key?(:polyglot_options) ? options[:polyglot_options] : POLYGLOT_OPTIONS
  java_options = 
    options.key?(:java_options) ? options[:java_options] : JAVA_OPTIONS

  begin
    joe = Joe.new(polyglot_options, java_options, options[:languages])
    puts joe.erase(option_string.lstrip)
    exit 0
  rescue SystemExit => e
  rescue Exception => e
    puts e.message
    exit 1
  end
end

