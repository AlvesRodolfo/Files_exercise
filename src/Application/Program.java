package Application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter with a path: ");
		String strPath = sc.nextLine();
		
		File path = new File(strPath);
		String sourceFolderStr = path.getParent();
		boolean success = new File (sourceFolderStr + "\\out").mkdir();
		String arquivo = sourceFolderStr + "\\out\\summary.csv";
		
		List<Product> list = new ArrayList<>();
		
		try (BufferedReader bf = new BufferedReader(new FileReader(strPath))) {
			String line = bf.readLine();
			
			while(line != null) {
				String[] vect = line.split(",");
				String name = vect[0];
				double price = Double.parseDouble(vect [1]);
				int quantity = Integer.parseInt(vect [2]);
				
				list.add(new Product(name, price, quantity));
				line = bf.readLine();
			}
	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))){
			for(Product l: list) {
				bw.write(l.toString());
				bw.newLine();
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		sc.close();



}
}
