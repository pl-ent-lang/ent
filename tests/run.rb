#!/usr/bin/env ruby

# Copyright 2015 The Zebu Authors. All rights reserved.

require 'optparse'
require 'ostruct'
require 'pp'
require 'mkmf'
require 'ptools'

$commands = {"pass" => "pass", "fail" => "fail"}
$entc = nil
$ent = nil

def fix_test(test)
  test.reverse.chomp("/.").reverse.chomp("/")
end

def get_main(test) 
  if !File.file?("#{test}/TEST")
    puts "TEST not found for test"
    exit 1
  end 

  file = File.open("#{test}/TEST")
  line = file.first
  match = /main:\s*(.*)\s*/.match(line)

  if (match[1] == nil)
    puts "main not found for test"
    exit 1
  end 
  return match[1]
end

def compile_files(test) 
  output = `#{$entc} -d build #{test}/*.java 2> /dev/null`
  return output, $?.exitstatus
end

def run_main(test,main)
  output = `#{$ent} -cp build #{test}.#{main} 2> /dev/null`
  return output, $?.exitstatus
end

# compile command is simple: must return no output
# and no error.
def run_test(test)
  command = nil
  if test.include?("pass") 
    command = "pass"
  elsif test.include?("fail")
    command = "fail"
  else
    puts "not a valid test"
    exit 1
  end

  main = get_main(test)

  output, result = compile_files(test)
  if (result != 0)
    puts "#{test} failed to compile" 
    exit 1
  end

  output, result = run_main(test, main)

  if (command == "pass" && result != 0)
    puts "#{test} failed: test should pass"
    exit 1
  elsif (command == "fail" && result == 0)
    puts "#{test} failed: test should fail"
    exit 1
  end
end 

options = OpenStruct.new
options.encoding = "utf8"
options.verbose = false

optparse = OptionParser.new do |opts| 
  opts.banner = "Usage: check.rb  -- [test] "

  opts.on("-v", "--verbose", "Run verbosely") do |v|
    options.verbose = v
  end

  opts.on("-h", "--help", "Show this message") do 
    puts opts
    exit
  end
end

optparse.parse!

if ARGV.size < 1 
  puts optparse.help
  exit
end

# Test for ent in path
$entc = File.which("entc")
$ent = File.which("ent")
if $entc.nil? || $ent.nil?
  puts "error: ent not found"
  exit 1
end

test = fix_test(ARGV[0])

if !Dir.exists?(test)
  puts "test (dir) not found"
  exit 1
end 
  
command = run_test(test)

exit 0 
