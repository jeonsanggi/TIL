package com.jremind.hadoop.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.jremind.hadoop.mapper.DepartureDelayCountMapper;
import com.jremind.hadoop.reducer.DelayCountReducer;


public class DepartureDelayCount {
	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();
		if (args.length != 2) {
			System.out.println("Usage: DepartureDelayCount <input> <output>");
			System.exit(2);
		}
		
		Job job = Job.getInstance(conf, "DepartureDelayCount");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setJarByClass(DepartureDelayCount.class);
		job.setMapperClass(DepartureDelayCountMapper.class);
		job.setReducerClass(DelayCountReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.waitForCompletion(true);
	}

}
