package com.jremind.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.jremind.map.WordCountMapper;
import com.jremind.reduce.WordCountReducer;


public class WordCount {
	public static void main(String[] args) throws Exception{
		// Configuration Job을 생성할때 Job이 필요한 요소들을 저장할 때 필요한 것들을 저쟁해놓음
		Configuration conf = new Configuration();
		
		if (args.length != 2) {
			System.out.println("Usage: WordCount <input> <output>");
			System.exit(2);
		}
		
		// 싱글턴 패턴
		Job job = Job.getInstance(conf, "WordCount");
		
		job.setJarByClass(WordCount.class);				// main으로 들어가는 클래스
		job.setMapperClass(WordCountMapper.class); 	// mapper로 들어가는 클래스
		job.setReducerClass(WordCountReducer.class); 	// Reduce로 들어가는 클래스
		
		job.setInputFormatClass(TextInputFormat.class);	 // 텍스트 인풋
		job.setOutputFormatClass(TextOutputFormat.class);// 텍스트 인풋
		
		job.setOutputKeyClass(Text.class);			// output key 클래스는 Text
		job.setOutputValueClass(IntWritable.class); // output value 클래스는 숫자

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}
}
