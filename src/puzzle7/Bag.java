package puzzle7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {
    private final String color;
    private int quantityInParentBag;  //Will only be set on childBags
    private List<Bag> childBags;

    public Bag(String inData) {
        int firstCommaPosition = inData.indexOf(",");
        color = inData.substring(0, firstCommaPosition);
        createChildBags(inData.substring(firstCommaPosition + 1));
    }

    public Bag(int quantityInParentBag, String color) {
        this.quantityInParentBag = quantityInParentBag;
        this.color = color;
    }

    private void createChildBags(String inData) {
        String[] childBagArray = inData.split(",");

        if (childBagArray[0].trim().startsWith("no")) {
            return;
        }

        childBags = Arrays.stream(childBagArray)
                .map(String::trim)
                .map(bag -> new Bag(Integer.parseInt(bag.substring(0, bag.indexOf(" "))),
                        bag.substring(bag.indexOf(" "))))
                .collect(Collectors.toList());
    }
}
