package Main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import data.*;
import searchandsort.Sort;

public class Main {

	public static String[] readFromFile(String path) {
		try {
			int i = 0;
			int length = Files.readAllLines(Paths.get(path), StandardCharsets.ISO_8859_1).size();
			String[] results = new String[length];
			for(String line : Files.readAllLines(Paths.get(path), StandardCharsets.ISO_8859_1)){
				results[i++] = line;
			}
			return results;
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		if(args.length == 2 ){
			PrintWriter writer = new PrintWriter("output.txt");
			ArrayList<String> output = new ArrayList<String>();
			String[] commands = readFromFile(args[0]);
			String[] data = readFromFile(args[1]);
			Sort.instantiateSorted(data);
			for(String com : commands){
				long starttime = System.currentTimeMillis();
				Command command = new Command(com);
				output.add("CommandText: " + "\"" + com + "\"");
				output.add("");
				output.add("Results:");
				String firstline = "";
				if()
				for(int i = 0; i < command.select.size(); i++){
					if(i == 0){
						firstline += command.select.get(i);
					} else {
						firstline += "\t" + command.select.get(i);
					}
				}
				output.add(firstline);
				for(PersonModel pm : command.merger()){
					
					if(command.isvalid){
						output.add(pm.toString(command.select));
						//System.out.println(pm.toString(command.select));
					} else{
						System.out.println("Invalid command!");
					}
				}
				output.add("---------------------------");
				long endtime = System.currentTimeMillis();
				output.add("Process time: " + (endtime - starttime) + " milliseconds");
				output.add("");
			}
			for(String out : output){
				System.out.println(out);
			}
		} else {
			System.out.println("Incorrect number of arguments.");
		}
	}
}
