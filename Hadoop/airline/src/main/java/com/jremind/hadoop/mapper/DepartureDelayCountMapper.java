package com.jremind.hadoop.mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.jremind.hadoop.common.AirlinePerformanceParser;

public class DepartureDelayCountMapper extends 
	Mapper<LongWritable, Text, Text, IntWritable>{
	
	private final static IntWritable outputValue = new IntWritable(1);
	private Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		AirlinePerformanceParser parser = new AirlinePerformanceParser(value);
		
		outputKey.set(parser.getYear() + "," + parser.getMonth());
		
		if(parser.getDepartureDelayTime() > 0) {
			context.write(outputKey, outputValue);
		}
	}
	
	

}
