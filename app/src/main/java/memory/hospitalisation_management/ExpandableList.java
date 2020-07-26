package memory.hospitalisation_management;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableList{
    
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        List<String>salle2 = new ArrayList<String>();
       salle2.add("Lit 1");
       salle2.add("Lit 2");
       salle2.add("Lit 3");
       salle2.add("Lit 4");

        List<String>salle3 = new ArrayList<String>();
       salle3.add("Lit 5");
       salle3.add("Lit 6");
       salle3.add("Lit 7");
       salle3.add("Lit 8");
       salle3.add("Lit 9");

        List<String>salle4 = new ArrayList<String>();
        salle3.add("Lit 10");
        salle3.add("Lit 11");
        salle3.add("Lit 12");
        salle3.add("Lit 13");
        salle3.add("Lit 14");

        List<String>salle5 = new ArrayList<String>();
        salle3.add("Lit 15");
        salle3.add("Lit 16");
        salle3.add("Lit 17");
        salle3.add("Lit 18");
        salle3.add("Lit 19");

        List<String>salle6 = new ArrayList<String>();
        salle3.add("Lit 20");
        salle3.add("Lit 21");
        salle3.add("Lit 22");
        salle3.add("Lit 23");
        salle3.add("Lit 24");
        List<String>salle7 = new ArrayList<String>();
        salle3.add("Lit 25");
        salle3.add("Lit 26");
        salle3.add("Lit 27");
        salle3.add("Lit 28");
        salle3.add("Lit 29");
        List<String>salle8 = new ArrayList<String>();
        salle3.add("Lit 30");
        salle3.add("Lit 31");
        salle3.add("Lit 32");
        salle3.add("Lit 33");
        salle3.add("Lit 34");

        expandableListDetail.put("SALLE 2",salle2);
        expandableListDetail.put("SALLE 3",salle3);
        expandableListDetail.put("SALLE 4",salle4);
        expandableListDetail.put("SALLE 5",salle5);
        expandableListDetail.put("SALLE 6",salle6);
        expandableListDetail.put("SALLE 7",salle7);
        expandableListDetail.put("SALLE 8",salle8);

        return expandableListDetail;
    }
}

