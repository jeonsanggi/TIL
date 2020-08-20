package com.jremind.map;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends 
	Mapper<LongWritable, Text, Text, IntWritable>{
	
	// IntWritable는 하둡에서 제공하는 데이터타입이다. IntWritable으로  Serialize를 제공한다.
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, 
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer strToken = new StringTokenizer(value.toString());
		while (strToken.hasMoreTokens()) {
			word.set(strToken.nextToken());
			context.write(word, one);
		}
	}
	
}
