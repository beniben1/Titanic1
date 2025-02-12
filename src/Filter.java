import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    private int pClass;
    private int[] idRange;
    private String subStringOfName;
    private String gender;
    private int sibSp;
    private int parch;
    private String ticket;
    private double[] fareRange;
    private String cabin;
    private char embarked;

    public Filter(){
        pClass = 0;
        idRange = new int[2];
        idRange[0] = -1;
        idRange[1] = -1;
        subStringOfName = "";
        gender = Constants.PASSENGER_GENDER_OPTIONS[0];
        sibSp = -1;
        parch = -1;
        ticket = "";
        fareRange = new double[2];
        fareRange[0] = -1;
        fareRange[1] = -1;
        cabin = "";
        embarked = ' ';
    }
    public List<Passenger> filterPassengers(List<Passenger> passengers){
        return passengers.stream()
                .filter(passenger -> this.pClass == 0 || this.pClass == passenger.getPClass())
                .filter(passenger ->
                        (idRange[0] == -1 && idRange[1] == -1) ||
                                (idRange[0] == -1 && passenger.getPassengerID() < idRange[1]) ||
                                (passenger.getPassengerID() >= idRange[0] && idRange[1] == -1)||
                                (passenger.getPassengerID() >= idRange[0] && passenger.getPassengerID() < idRange[1]))
                .filter(passenger -> passenger.getName().contains(subStringOfName))
                .filter(passenger -> this.gender.equals(Constants.PASSENGER_GENDER_OPTIONS[0])||passenger.getSex().equals(this.gender))
                .filter(passenger -> this.sibSp == -1 || passenger.getSibSP() == this.sibSp)
                .filter(passenger -> this.parch == -1 || passenger.getParch() == this.parch)
                .filter(passenger -> passenger.getTicket().contains(String.valueOf(this.ticket)))
                .filter(passenger ->
                        (fareRange[0] == -1 && fareRange[1] == -1) ||
                                (fareRange[0] == -1 && passenger.getFare() < fareRange[1]) ||
                                (passenger.getFare() >= fareRange[0] && fareRange[1] == -1)||
                                (passenger.getFare() >= fareRange[0] && passenger.getFare() < fareRange[1]))
                .filter(passenger -> passenger.getCabin().contains(String.valueOf(cabin)))
                .filter(passenger -> this.embarked == ' '|| passenger.getEmbarked() == this.embarked)
                .collect(Collectors.toList());
    }

    public void setPClass(int pClass) {
        this.pClass = pClass;
    }

    public void setIdRange(int index, int value) {
        this.idRange[index] = value;
    }

    public void setSubStringOfName(String subStringOfName) {
        this.subStringOfName = subStringOfName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSibSp(int sibSp) {
        this.sibSp = sibSp;
    }

    public void setParch(int parch) {
        this.parch = parch;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setFareRange(int index, double value) {
        this.fareRange[index] = value;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public void setEmbarked(char embarked) {
        this.embarked = embarked;
    }
}
