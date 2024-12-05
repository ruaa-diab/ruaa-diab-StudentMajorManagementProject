package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandling {

	LinkedList<String> fileLines1 = new LinkedList<>();
	LinkedList<String> fileLines2 = new LinkedList<>();
	LinkedList<String> f = new LinkedList<>();
	LinkedList<String> f2 = new LinkedList<>();

	public void file2Handle(File file) throws FileNotFoundException {

		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {

			String line = scanner.nextLine();

			fileLines2.insert(line);

		}
		scanner.close();

	}

	public DlinkedList<MajorData> makeMajors() {
		if (fileLines2 == null || fileLines2.size() <= 0) {
			return new DlinkedList<>();
		}
		DlinkedList<MajorData> majors = new DlinkedList<>();

		for (int i = 0; i < fileLines2.size(); i++) {

			String[] parts = fileLines2.getAt(i).split(";");
			if (parts.length != 4) {
				f2.insert(fileLines2.getAt(i));
				fileLines2.delete(fileLines2.getAt(i));
				continue;
			}
			if (parts[0].trim().compareToIgnoreCase("major") == 0
					|| parts[1].trim().compareToIgnoreCase("Acceptance grade") == 0
					||
					parts[2].trim().compareToIgnoreCase("TawjihiWeight")==0||parts[3].trim().compareToIgnoreCase("PlacementTestWeight")==0) {
				continue;
			}

try {
			

	MajorData major = new MajorData(parts[0].trim(),
			Integer.parseInt(parts[1].trim()),
			Double.parseDouble(parts[2].trim()),
			Double.parseDouble(parts[3].trim()));
			
			
			
			majors.insert(major);
			
			
			

		} catch (IllegalArgumentException r) {
			f2.insert(fileLines2.getAt(i));
			fileLines2.delete(fileLines2.getAt(i));
			continue;

		} catch (Exception e) {
			System.out.println("Error processing student: " + parts[1].trim() + ". " + e.getMessage());
			continue;
		}

		}
		return majors;
	}
	
	
	
	
	
	
	
	

	public MajorData findingMajor(DlinkedList<MajorData> majors, String n) {

		for (int i = 0; i < majors.size(); i++) {
			if (majors.getAt(i).getMajor().compareToIgnoreCase(n) == 0) {
				MajorData major = majors.getAt(i);
				
				return major;

			}
		}
		return null;

	}

	public void file1Handle(File file) throws FileNotFoundException {

		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			fileLines1.insert(line);

		}
		scanner.close();

	}

	public LinkedList<Student> makeStudents(DlinkedList<MajorData> majors) throws NullPointerException{
		if (fileLines1 == null || fileLines1.size() <= 0) {
			return null;
		}
		LinkedList<Student> students = new LinkedList<>();

		for (int i = 0; i < fileLines1.size(); i++) {
			String[] parts = fileLines1.getAt(i).split(";");
			if(parts.length!=5) {
				fileLines1.delete(fileLines1.getAt(i));
				f.insert(fileLines1.getAt(i));
				continue;
				
			}

			if (parts[0].trim().compareToIgnoreCase("studentid") == 0
					|| parts[1].trim().compareToIgnoreCase("name") == 0
					|| parts[4].trim().compareToIgnoreCase("chosen major") == 0) {
				continue;
			}

			try {
				String majorName = parts[4].trim();
				MajorData major = findingMajor(majors, majorName);
			
				
				
				
				Student std = new Student(parts[0].trim(), parts[1].trim(), // Name
						Double.parseDouble(parts[2].trim()), Integer.parseInt(parts[3].trim()), major); // Chosen Major
				if(major.getAcceptanceg()<=std.getAdMark()) {
					major.getStudents().insert(std);				}
				std.setcMajor(major);
				students.insert(std);
				

			} catch (IllegalArgumentException r) {
				f.insert(fileLines1.getAt(i));
				fileLines1.delete(fileLines1.getAt(i));
				continue;

			
			
			} catch (Exception e) {
				System.out.println("Error processing student: " + parts[1].trim() + ". " + e.getMessage());
				continue;
			}
		}
		return students;
	}

}
