package helloWorld;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import Thing.Thing;

public class app {
	public static void main(String[] args) {

		String filePath = "C:\\Users\\apitz_000\\Desktop\\CSVParser\\DATfile.txt";
		String writeFile = "C:\\\\Users\\\\apitz_000\\\\Desktop\\\\CSVParser\\\\writeFile.csv";
		String delimToRemove = "::";
		String newDelim = ",";
		String currentLine;
		String[] currentIndex;
		PrintWriter pw;

		Map<String, Thing> things = new HashMap<>();

		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)));

			sc.nextLine();

			while (sc.hasNextLine()) {

				currentLine = sc.nextLine();

				currentIndex = currentLine.split(delimToRemove);

				Thing thing = new Thing();
				thing.setName(currentIndex[0]);
				thing.setNumber(currentIndex[1]);
				thing.setSize(currentIndex[2]);

				String thingKey = thing.getName();

				things.put(thingKey, thing);

			}

			for (Entry<String, Thing> e : things.entrySet()) {

				Thing t = e.getValue();
				System.out.print(t.getName());
				System.out.print(t.getNumber());
				System.out.println(t.getSize());
			}

			pw = new PrintWriter(new FileWriter(writeFile));

			for (Entry<String, Thing> e : things.entrySet()) {
				Thing t = e.getValue();
				pw.println(t.getName() + newDelim + t.getNumber() + newDelim + t.getSize());
			}
			pw.flush();
			pw.close();

		} catch (Exception x) {
			x.printStackTrace();
		}

	}

}
