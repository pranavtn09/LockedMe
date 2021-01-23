package LockMe;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FileProject {
	static Scanner read=new Scanner(System.in);
	static int a;
	private static void  menu() {
		try {
		System.out.println("\t\tLockedMe.com \nDeveloped by \nPranav T N");
		System.out.println("\n\t(1) Print File names in Ascending order\n\t(2) Other Operations\n\t(3) Exit \n\n\tEnter an Option:");
		int a=Integer.parseInt(read.nextLine());
		switch(a) {
		case 1:
			
			ascending();
			break;
		case 2:
			submenu();
			break;
		case 3:
			System.out.println("Program Exited!!");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Option!!!");
			menu();
			break;
		}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid Option!!!");
			menu();
		}
	}
	private static void submenu() {
		System.out.println("\n\t(1) Add File\n\t(2) Delete File\n\t(3) Search File\n\t(4) Back to Main menu");
		int b=Integer.parseInt(read.nextLine());
		switch(b) {
		case 1:
			
			addFile();
			break;
		case 2:
			deleteFile();
			break;
		case 3:
			searchFile();
			break;
		case 4:
			menu();
			break;
		}
	}
	private static List<String> addtoList(File files,List<String> list,String path){
		//Set<String> list=new HashSet<String>();
		try {
		File[] filess=files.listFiles();
		if(filess==null) {
			Path p=Paths.get(path);
			Files.exists(p, null);
			return null;
		}
		for(File each:filess) {
			if(each.isDirectory()){
				addtoList(each,list,path);
				}
			else {
				list.add(each.getName().toString());
			}
			}
		return list;
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println("Invalid Path!!");
		menu();
		System.exit(0);
		return null;
	}
	}
	private static void ascending() {
		System.out.println("Enter root Directory path((Eg:E:\\DwnlData\\Documents)):");
		String path=read.nextLine();
		File files=new File(path);
		List<String> list=new ArrayList<String>();
		addtoList(files, list,path);
		Collections.sort(list,String.CASE_INSENSITIVE_ORDER);
		if(list.isEmpty()) {
			System.out.println("No Files");
		}
		else {
		System.out.println("List of Files in route folder and its subfolder\n");
		for(String s:list) {
			System.out.println(s);
		}
		}
		menu();
	}
	private static void addFile() {
		System.out.println("\n\tEnter Existing path with New File name(Eg:\\E:\\DwnlData\\Documents\\test.txt):");
		String path=read.nextLine();
		File file=new File(path);
		boolean rslt;
		try {
			rslt = file.createNewFile();
			if(rslt) {
				System.out.println("File Created");
				submenu();
			}
			else {
				System.out.println("File Already Exist!!!");
				submenu();
			}
		} catch (NoSuchFileException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Path!!!");
			submenu();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Invalid pat!!h");
		}
		
	}
	private static void deleteFile() {
		System.out.println("Enter Absolute path(E:\\DwnlData\\Documents):");
	    String path=read.nextLine();		
	    System.out.println("Name of the file to be Deleted:");
	    String name=read.nextLine();
	    String ps=path.concat("\\"+name);
	    File files=new File(path);
	    int flag=0;
	    File[] filess=files.listFiles();
		for(File f:filess) {
			if(f.getName().equals(name)) {
				flag=1;
				break;
			}
		}
		try {
			if(flag==1) {
			Path p=Paths.get(ps);
			Files.delete(p);
			System.out.println("File Successfully Deleted");
			submenu();
			}else {
				System.out.println("File not Found");
				submenu();
			}
		}catch(NoSuchFileException fs) {
			System.out.println("File Not Found");
			submenu();
			
		}catch (InvalidPathException e) {
			// TODO: handle exception
			System.out.println("Invalid Path!!!");
			submenu();
		}
		catch(DirectoryNotEmptyException fs) {
			System.out.println("It is a Directory");
			submenu();
		}
	
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private static void searchFile()  {
		System.out.println("Enter  Absolute path(Eg:E:\\DwnlData\\Documents):");
		String path=read.nextLine();
		System.out.println("Enter Filename:");
		String name=read.nextLine();
		String p=path.concat("\\"+name);
		File files=new File(path);
		File file=new File(p);
		int flag=0;
		File[] filess=files.listFiles();
		for(File f:filess) {
			if(f.getName().equals(name)&&!file.isDirectory()) {
				flag=1;
				break;
			}
		}
		if(flag==1) {
			System.out.println("File Found");	
			submenu();
		}
		else {
		System.out.println("File not found");
		submenu();
		}
	}
	
	
	public static void main(String[] args)  {
		
		menu();
	}
}
