public class Passenger implements Comparable<Passenger> {
    private final int passengerID;
    private final boolean survived;
    private final int pClass;
    private final String name;
    private final String sex;
    private final double age;
    private final int sibSP;
    private final int parch;
    private final String ticket;
    private final double fare;
    private final String cabin;
    private final char embarked;

    public Passenger(int passengerID, boolean survived, int pClass, String name, String sex, double age, int sibSP, int parch, String ticket, double fare, String cabin, char embarked) {
        this.passengerID = passengerID;
        this.survived = survived;
        this.pClass = pClass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sibSP = sibSP;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
    }
    public String getFormattedName(){
        StringBuilder firstName = new StringBuilder();
        StringBuilder lastName = new StringBuilder();
        boolean skip = false;
        boolean doLastName = true;
        for (int i = 0; i < this.name.length(); i++){
            char current = name.charAt(i);
            if (current == ','){
                skip = true;
                continue;
            }
            else if (current == '.'){
                skip = false;
                doLastName = false;
                continue;
            }
            if (skip){
                continue;
            }
            if (doLastName){
                lastName.append(current);
            }
            else {
                firstName.append(current);
            }
        }
        return String.valueOf(firstName.append(" ").append(lastName));
    }

    public int getPassengerID() {
        return passengerID;
    }

    public boolean isSurvived() {
        return survived;
    }

    public int getPClass() {
        return pClass;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public double getAge() {
        return age;
    }

    public int getSibSP() {
        return sibSP;
    }

    public int getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public double getFare() {
        return fare;
    }

    public String getCabin() {
        return cabin;
    }

    public char getEmbarked() {
        return embarked;
    }
    public String toString(){
        return passengerID + "," + (survived? 1: 0) + "," + pClass + "," + getFormattedName() + "," + sex + "," + age + "," + sibSP + "," + parch + "," + ticket + "," + fare + "," + cabin + "," + embarked;
    }
    @Override
    public int compareTo(Passenger o) {
        return this.getFormattedName().compareTo(o.getFormattedName());
    }
}
