import java.util.HashMap;
import java.util.Scanner;
public class versions {
        public static void main(String[] args) {
            HashMap<String, Integer> map = new HashMap<>();
            Scanner scanner = new Scanner(System.in);
            // Add some data to the map
            map.put("key1", 1);
            map.put("key2", 1);
            map.put("key3", 0);
            Integer version=0;
            System.out.println("enter the file name : ");
    String name=scanner.nextLine();
            // Check if the map contains a particular key
            if (map.containsKey(name)) {
                System.out.print("A file with this name already exists. Please enter a new name: ");
                System.out.println("enter 1 for new name 2 for new version");
             int ch=0;
             ch=scanner.nextInt();
                if (ch==1){
                    name=scanner.nextLine();
                    map.put(name, version);
                }
                else if (ch ==2){
                   version=map.get(name)+1;
                    map.put(name,version);
                }
            } else {
                map.put(name,version);

            }
            System.out.println(map);
            }

    }

