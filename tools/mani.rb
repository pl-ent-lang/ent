#!/usr/bin/env ruby

require 'optparse'
require 'find'
require "nokogiri"

def find_manifest(topdir)
  manifest = nil
  Find.find("#{topdir}") do |path|
    if path =~ /.*AndroidManifest.xml$/
      unless manifest == nil
        raise Exception.new("More than one AndroidManifest.xml exists!") 
      end
      manifest = path
    end
  end
  return manifest
end

def search_manifest(manifest)
  xml = Nokogiri::XML(File.open(manifest))

  clazz = nil
  package = xml.xpath("//manifest").first()["package"] 

  xml.xpath("//activity").each do |activ|
    activ.xpath("intent-filter//action").each do |action|
      next unless action["android:name"] =~ /.*MAIN$/
      raise Exception.new("Multiple MAIN activities!") unless !clazz
      clazz = activ["android:name"] 
    end
  end

  return "#{package}#{clazz}"
end

if __FILE__ == $0
  options = {}
  options[:exact] = false

  ARGV << '-h' if ARGV.empty?

    
  optparse = OptionParser.new do |opts|
    opts.banner = "Usage: flat.rb [options] [top dir]"

    opts.on("-e", "--exact", "You know the manifest file. Use it.") do |e|
      options[:exact] = true
    end

    opts.on("-h", "--help", "Show this message.") do 
      puts opts
      exit 1
    end
  end

  optparse.parse!

  topdir = ARGV.pop

  if options[:exact]
    unless File.exists?(topdir)
      puts "#{topdir} does not exist!"
      exit 1
    end
  else
    unless Dir.exists?(topdir)
      puts "#{topdir} does not exist!"
      exit 1
    end
  end

  begin
    unless options[:exact]
      file = find_manifest(topdir)
      puts file
    else
      file = topdir
    end
    full_clazz = search_manifest(file)
    puts full_clazz
    exit 0
  rescue SystemExit => e
  rescue Exception => e
    puts e.message
    exit 1
  end
end
