package Day1to9.puzzle7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {
    private String color;

    private int quantityInParentBag;  //Will only be set on childBags
    private List<Bag> childBags;

    public Bag(String inData) {
        int firstCommaPosition = inData.indexOf(",");
        color = inData.substring(0, firstCommaPosition);
        if (color.charAt(color.length() - 1) == 's') {
            color = color.substring(0, color.length() - 1);
        }
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
                .map(bag -> {
                    if (bag.charAt(bag.length() - 1) == 's') {
                        bag = bag.substring(0, bag.length() - 1);
                    }
                    return new Bag(Integer.parseInt(bag.substring(0, bag.indexOf(" "))),
                            bag.substring(bag.indexOf(" ")).trim());
                })
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return color + ", " + quantityInParentBag + " " + childBags;
    }

    public boolean containsBag(String otherColor) {
        if (childBags == null) {
            return false;
        }
        for (Bag bag : childBags) {
            if (bag.color.trim().equals(otherColor.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNotBeenChecked() {
        if (Puzzle7.getCheckedBags().contains(this)) {
            return false;
        } else {
            Puzzle7.getCheckedBags().add(this);
            return true;
        }
    }

    public long getParentBags() {
        return Puzzle7.getAllBags().stream()
                .filter(bag -> bag.containsBag(this.color))
                .filter(Bag::hasNotBeenChecked)
                .map(Bag::getParentBags)
                .reduce(0L, Long::sum) + 1;
    }

    public String getColor() {
        return color;
    }

    public void loadChildBags() {
        Bag foundBag = Puzzle7.getAllBags().stream()
                .filter(bag -> bag.color.equals(this.color))
                .findFirst()
                .orElseThrow();
        this.childBags = foundBag.childBags;
    }

    public long countAllSubBags() {
        if (childBags == null) {
            return 1;
        }
        long subBagsSum = 0;
        for (Bag childBag : childBags) {
            childBag.loadChildBags();
            subBagsSum += childBag.quantityInParentBag * childBag.countAllSubBags();
        }
        subBagsSum++;
        return subBagsSum;
    }
}
