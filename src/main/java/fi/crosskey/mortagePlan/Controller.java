package fi.crosskey.mortagePlan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Annotation
@RestController

// Class
public class Controller {

    ArrayList<Prospect> prospects;

    // Constructor of this class
    Controller() {
        prospects= readFile("prospects.txt");
    }


    //I have used Postman API platform to test my GET/PUT request
    @PutMapping("prospects/{name}/{loan}/{interest}/{years}")
    void addProspect(@PathVariable String name,@PathVariable double loan,@PathVariable double interest , @PathVariable int years ) throws IOException {
        Prospect p = new Prospect(name, loan, interest, years);
        FileWriter fw = new FileWriter("prospects.txt",true);
        BufferedWriter writer = new BufferedWriter(fw);
        String prospect ="\n"+ name + ","+loan+","+interest+","+years;
        writer.append(prospect);
        writer.close();
        fw.close();
        this.prospects.add(p);
    }

    // Annotation
    @GetMapping ("/prospects/")
    // Method
    ArrayList<Prospect> getProspects() {
        for (int i =0 ; i<prospects.size();i++
        ) {
            System.out.println("*************************************************************");
            System.out.println("Prospect " + (i+1)+": " + prospects.get(i).getName() + " wants to borrow " +prospects.get(i).getLoan() + " € for a period of " + prospects.get(i).getYears() + " years and pay" +
                    String.format("%.2f", prospects.get(i).getMonthlyPayment()) + "€ each month");

        }

        return this.prospects;
    }
    /**
     *Method for reading a file with customers data
     * @param s The path of the file
     * @return An ArrayList<Customer> where every element in the ArrayList represent a row from the file that was red
     */
    public static ArrayList<Prospect> readFile(String s) {
        ArrayList<Prospect> customers = new ArrayList<>();
        try {
            File file = new File(s);
            Scanner scanner = new Scanner(file);
            //Skip the columns names
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (!data.contains(",")) {
                    continue;
                }

                if (data.contains("\"")) {
                    String[] rowWithQuote = data.trim().split("\"", -1);
                    rowWithQuote[1] = rowWithQuote[1].replace(",", " ");
                    String[] row = rowWithQuote[2].split(",", -1);
                    customers.add(new Prospect(rowWithQuote[1], Double.parseDouble(row[1]), Double.parseDouble(row[2]), Integer.parseInt(row[3])));
                } else {
                    String[] row = data.trim().split(",", -1);

                    customers.add(new Prospect(row[0], Double.parseDouble(row[1]), Double.parseDouble(row[2]), Integer.parseInt(row[3])));

                }

            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return customers;
    }
}
