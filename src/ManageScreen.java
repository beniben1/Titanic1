import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ManageScreen extends JPanel {
    private List<Passenger> passengers;
    private final int x;
    private int componentY;
    private int componentYMargin;

    public ManageScreen(int x, int y, int width, int height) {
        this.x = x;
        Filter filter = new Filter();
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file

        AtomicInteger fileCount = new AtomicInteger();
        File fileCountStorage = new File(Constants.PATH_TO_FILE_COUNT);

        try {
            BufferedReader fileCountReader = new BufferedReader(new FileReader(fileCountStorage));
            String countStr = fileCountReader.readLine();
            if (countStr != null) {
                fileCount.set(Integer.parseInt(countStr));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (file.exists()) {
            initializeList(file);
            componentY = y;
            componentYMargin = Constants.LABEL_HEIGHT + Constants.MARGIN_FROM_TOP;
            this.setLayout(null);
            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);

            JLabel pClassLabel = addLabel("Passenger class: ");
            JComboBox<String> pClassComboBox = addComboBox(pClassLabel, Constants.PASSENGER_CLASS_OPTIONS);
            pClassComboBox.addActionListener((e) -> {
                filter.setPClass(pClassComboBox.getSelectedIndex());
            });
            componentY += componentYMargin;

            addLabel("ID range: ");
            componentY += componentYMargin;

            JLabel idRangeMinLabel = addLabel("Min: ");
            JTextArea idRangeMinTextBox = addTextArea(idRangeMinLabel);
            JButton submitMinIdRange = addButton(idRangeMinTextBox, "Submit");
            submitMinIdRange.addActionListener((e -> {
                try {
                    int minRange = Integer.parseInt(idRangeMinTextBox.getText());
                    filter.setIdRange(0, minRange);
                } catch (Exception error) {
                    if (idRangeMinTextBox.getText().isEmpty()) {
                        filter.setFareRange(0, -1);
                    } else {
                        idRangeMinTextBox.setText("Please enter\na single number");
                    }
                }
            }));
            componentY += componentYMargin;

            JLabel idRangeMaxLabel = addLabel("Max: ");
            JTextArea idRangeMaxTextBox = addTextArea(idRangeMaxLabel);
            JButton submitMaxIdRange = addButton(idRangeMaxTextBox, "Submit");
            submitMaxIdRange.addActionListener((e -> {
                try {
                    int maxRange = Integer.parseInt(idRangeMaxTextBox.getText());
                    filter.setIdRange(1, maxRange);
                } catch (Exception error) {
                    if (idRangeMaxTextBox.getText().isEmpty()) {
                        filter.setIdRange(1, -1);
                    } else {
                        idRangeMaxTextBox.setText("Please enter\na single number");
                    }
                }
            }));
            componentY += componentYMargin;

            JLabel substringOfNameLabel = addLabel("Name/part of name: ");
            JTextArea substringOfNameTextBox = addTextArea(substringOfNameLabel);
            JButton submitSubstringOfName = addButton(substringOfNameTextBox, "Submit");
            submitSubstringOfName.addActionListener((e -> {
                filter.setSubStringOfName(substringOfNameTextBox.getText());
            }));
            componentY += componentYMargin;

            JLabel genderLabel = addLabel("Passenger Gender: ");
            JComboBox<String> genderComboBox = addComboBox(genderLabel, Constants.PASSENGER_GENDER_OPTIONS);
            genderComboBox.addActionListener((e) -> {
                filter.setGender((String) genderComboBox.getSelectedItem());
            });
            componentY += componentYMargin;

            JLabel sibSpLabel = addLabel("Number of siblings plus spouse: ");
            JTextArea sibSpTextBox = addTextArea(sibSpLabel);
            JButton submitSibSp = addButton(sibSpTextBox, "Submit");
            submitSibSp.addActionListener((e -> {
                try {
                    int sibSp = Integer.parseInt(sibSpTextBox.getText());
                    filter.setSibSp(sibSp);
                } catch (Exception error) {
                    sibSpTextBox.setText("Please enter\na single number");
                }
            }));
            componentY += componentYMargin;

            JLabel parchLabel = addLabel("Number of parents and children: ");
            JTextArea parchTextBox = addTextArea(parchLabel);
            JButton submitParch = addButton(parchTextBox, "Submit");
            submitParch.addActionListener((e -> {
                try {
                    int parch = Integer.parseInt(parchTextBox.getText());
                    filter.setParch(parch);
                } catch (Exception error) {
                    parchTextBox.setText("Please enter\na single number");
                }
            }));
            componentY += componentYMargin;

            JLabel ticketLabel = addLabel("Ticket/part of ticket: ");
            JTextArea ticketTextBox = addTextArea(ticketLabel);
            JButton submitTicket = addButton(ticketTextBox, "Submit");
            submitTicket.addActionListener((e) -> {
                filter.setTicket(ticketTextBox.getText());
            });
            componentY += componentYMargin;

            addLabel("Fare range: ");
            componentY += componentYMargin;

            JLabel fareRangeMinLabel = addLabel("Min: ");
            JTextArea fareRangeMinTextBox = addTextArea(fareRangeMinLabel);
            JButton submitMinFareRange = addButton(fareRangeMinTextBox, "Submit");
            submitMinFareRange.addActionListener((e -> {
                try {
                    double minRange = Double.parseDouble(fareRangeMinTextBox.getText());
                    filter.setFareRange(0, minRange);
                } catch (Exception error) {
                    if (fareRangeMinTextBox.getText().isEmpty()) {
                        filter.setFareRange(0, -1);
                    } else {
                        fareRangeMinTextBox.setText("Please enter\na single number");
                    }
                }
            }));
            componentY += componentYMargin;

            JLabel fareRangeMaxLabel = addLabel("Max: ");
            JTextArea fareRangeMaxTextBox = addTextArea(fareRangeMaxLabel);
            JButton submitMaxFareRange = addButton(fareRangeMaxTextBox, "Submit");
            submitMaxFareRange.addActionListener((e -> {
                try {
                    double maxRange = Double.parseDouble(fareRangeMaxTextBox.getText());
                    filter.setFareRange(1, maxRange);
                } catch (Exception error) {
                    if (fareRangeMaxTextBox.getText().isEmpty()) {
                        filter.setFareRange(1, -1);
                    } else {
                        fareRangeMaxTextBox.setText("Please enter\na single number");
                    }
                }
            }));
            componentY += componentYMargin;

            JLabel cabinLabel = addLabel("Passenger Cabin: ");
            JTextArea cabinTextBox = addTextArea(cabinLabel);
            JButton submitCabin = addButton(cabinTextBox, "Submit");
            submitCabin.addActionListener((e -> {
                filter.setCabin(cabinTextBox.getText());
            }));
            componentY += componentYMargin;

            JLabel embarkedLabel = addLabel("Add port embarked on: ");
            JComboBox<String> embarkedComboBox = addComboBox(embarkedLabel, Constants.PORTS_EMBARKED_OPTIONS);
            embarkedComboBox.addActionListener(e -> {
                if (embarkedComboBox.getSelectedIndex() == 0) {
                    filter.setEmbarked(' ');
                } else {
                    filter.setEmbarked(((String) Objects.requireNonNull(embarkedComboBox.getSelectedItem())).charAt(0));
                }
            });
        }
        componentY += componentYMargin;

        JLabel submitLabel = addLabel("Submit filters");
        JButton submitButton = addButton(submitLabel, "Submit");
        componentY += componentYMargin;

        TextField survivedText = new TextField();
        survivedText.setEditable(false);
        survivedText.setBounds(Constants.MARGIN_FROM_LEFT, componentY, width, Constants.LABEL_HEIGHT);
        Font font = new Font("Times New Roman", Font.BOLD, 12);
        survivedText.setFont(font);
        this.add(survivedText);
        submitButton.addActionListener((e -> {
            List<Passenger> filteredPassengers = filter.filterPassengers(passengers);
            int survived = filteredPassengers.stream().filter(Passenger::isSurvived).toList().size();
            survivedText.setText("Total rows: " + filteredPassengers.size() + "(survived: " + survived + ", not survived: " + (filteredPassengers.size() - survived) + ")");

            filteredPassengers = filteredPassengers.stream().sorted().collect(Collectors.toList());

            StringBuilder logBuilder = new StringBuilder();
            logBuilder.append("PassengerId,Survived,Pclass,Name,Sex,Age,SibSp,Parch,Ticket,Fare,Cabin,Embarked\n");
            for (Passenger filteredPassenger : filteredPassengers) {
                logBuilder.append(filteredPassenger.toString()).append("\n");
            }
            String log = String.valueOf(logBuilder);

            File csvFile = new File("titanic-ex-master\\src\\data\\csv." + fileCount.getAndIncrement() + ".csv");
            try {
                FileWriter fileCountWriter = new FileWriter(fileCountStorage);
                fileCountWriter.write(String.valueOf(fileCount.get()));
                FileWriter fileWriter = new FileWriter(csvFile);
                fileWriter.write(log);
                fileWriter.close();
                fileCountWriter.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        JButton statisticsButton = addButton(Constants.MARGIN_FROM_LEFT * 4 + Constants.LABEL_WIDTH + Constants.BUTTON_WIDTH + Constants.COMBO_BOX_WIDTH,
                0,"Statistics");
        statisticsButton.setFont(new Font("Times New Roman",Font.BOLD,10));
        statisticsButton.addActionListener((e -> {
            getStatistics();
        }));
        this.add(statisticsButton);

        JComboBox<String> statisticsSortByParameter = addComboBox(statisticsButton.getX(),
                statisticsButton.getY() + statisticsButton.getHeight() + Constants.MARGIN_FROM_TOP,Constants.STATISTICS_PARAMETER_OPTIONS);

        JTextArea statisticsTextArea = addTextArea(statisticsSortByParameter.getX(),
                statisticsSortByParameter.getY() + statisticsSortByParameter.getHeight() + Constants.MARGIN_FROM_TOP);
        statisticsTextArea.setEditable(false);
        statisticsTextArea.setSize(statisticsTextArea.getWidth() * 2,Constants.WINDOW_HEIGHT - statisticsTextArea.getY());
        statisticsSortByParameter.addActionListener((e -> {
            statisticsTextArea.setText(statisticsByParameter((String) statisticsSortByParameter.getSelectedItem()));
        }));
        this.setVisible(true);
    }
    private void getStatistics(){
        String statisticsInfo = "";
        Map<Integer,List<Passenger>> passengersInClass = new HashMap<>();
        for (int i = 1; i < Constants.PASSENGER_CLASS_OPTIONS.length; i++){
            passengersInClass.put(i,new LinkedList<>());
        }
        for (Passenger passenger: passengers){
            passengersInClass.get(passenger.getPClass()).add(passenger);
        }
        statisticsInfo += "By class:\n";
        for (int i = 1; i <= Constants.PASSENGER_CLASS_OPTIONS.length - 1; i++){
            statisticsInfo += "Class " + i + ": " + (float)passengersInClass.get(i).stream().filter(Passenger::isSurvived).count()/passengersInClass.get(i).size() * 100 + "% survived\n";
        }

        statisticsInfo += "\nBy gender:\n";
        Map<String,List<Passenger>> passengersOfGender = new HashMap<>();
        passengersOfGender.put("male",new LinkedList<>());
        passengersOfGender.put("female", new LinkedList<>());
        for (Passenger passenger: passengers){
            passengersOfGender.get(passenger.getSex()).add(passenger);
        }
        statisticsInfo += "male: " + (float)passengersOfGender.get("male").stream().filter(Passenger::isSurvived).count() / passengersOfGender.get("male").size() * 100 + "% survived\n";
        statisticsInfo += "female: " + (float)passengersOfGender.get("female").stream().filter(Passenger::isSurvived).count() / passengersOfGender.get("female").size() * 100 + "% survived\n";

        statisticsInfo += "\nBy age group:\n";
        Map<Integer,List<Passenger>> passengersOfAge = new HashMap<>();
        for (int i = 0; i <= Constants.MAX_AGE_GROUP; i+= Constants.AGE_GROUP_DIFFERENCE){
            passengersOfAge.put(i,new LinkedList<>());
            if (i == 0){
                i++;
            }
        }
        for (Passenger passenger: passengers){
            int range = (int) (passenger.getAge() / Constants.AGE_GROUP_DIFFERENCE);
            range = range * Constants.AGE_GROUP_DIFFERENCE + 1;
            if (passenger.getAge() <= Constants.AGE_GROUP_DIFFERENCE){
                range = 0;
            }
            else if (range > Constants.MAX_AGE_GROUP){
                range = Constants.MAX_AGE_GROUP;
            }
            passengersOfAge.get(range).add(passenger);
        }
        for (int key: passengersOfAge.keySet().stream().sorted().toList()){
            statisticsInfo += "Passengers of ages " + (key < Constants.MAX_AGE_GROUP? key + " - " + (key == 0? Constants.AGE_GROUP_DIFFERENCE: key + 9): Constants.MAX_AGE_GROUP + "+ ") + ": "
                    + (float)passengersOfAge.get(key).stream().filter(Passenger::isSurvived).count()
                    / passengersOfAge.get(key).size() * 100 + "% survived\n";
        }

        statisticsInfo += "\nBy with VS without family:\n";
        Map<Boolean,List<Passenger>> withOrWithoutFamilyPassengers = new HashMap<>();
        withOrWithoutFamilyPassengers.put(true,new LinkedList<>());
        withOrWithoutFamilyPassengers.put(false, new LinkedList<>());
        for (Passenger passenger: passengers){
            boolean hadFamily = passenger.getParch() + passenger.getSibSP() >= 1;
            withOrWithoutFamilyPassengers.get(hadFamily).add(passenger);
        }
        statisticsInfo += "With family: " + (float) withOrWithoutFamilyPassengers.get(true).stream().filter(Passenger::isSurvived).count()/withOrWithoutFamilyPassengers.get(true).size() * 100 + "% survived\n";
        statisticsInfo += "Without family: " + (float) withOrWithoutFamilyPassengers.get(false).stream().filter(Passenger::isSurvived).count()/withOrWithoutFamilyPassengers.get(false).size() * 100 + "% survived\n";

        statisticsInfo += "\nBy fare range:\n";
        Map<Integer,List<Passenger>> passengersByFarePrice = new HashMap<>();
        for (int i = 0; i <= Constants.MAX_FARE_GROUP; i+= 10){
            if (i == 21)
                continue;
            passengersByFarePrice.put(i,new LinkedList<>());
            if (i == 0){
                i++;
            }
        }
        for (Passenger passenger: passengers){
            int range;
            if (passenger.getFare() <= 10){
                range = 0;
            }
            else if (passenger.getFare() < Constants.MAX_FARE_GROUP){
                range = 11;
            }
            else {
                range = Constants.MAX_FARE_GROUP;
            }
            passengersByFarePrice.get(range).add(passenger);
        }
        for (int key: passengersByFarePrice.keySet().stream().sorted().toList()){
            statisticsInfo += "Passengers who paid " + (key == 0? "0 - 10": key == 11? "11 - " + (Constants.MAX_FARE_GROUP - 1): Constants.MAX_FARE_GROUP + "+ ") + " pounds: "
                    + (float)passengersByFarePrice.get(key).stream().filter(Passenger::isSurvived).count()
                    / passengersByFarePrice.get(key).size() * 100 + "% survived\n";
        }

        statisticsInfo += "\nBy port:\n";
        Map<Character,List<Passenger>> passengersByPort = new HashMap<>();
        for (int i = 1; i < Constants.PORTS_EMBARKED_OPTIONS.length; i++){
            passengersByPort.put(Constants.PORTS_EMBARKED_OPTIONS[i].charAt(0),new LinkedList<>());
        }
        for (Passenger passenger: passengers){
            if (passenger.getEmbarked() == ' ')
                continue;
            passengersByPort.get(passenger.getEmbarked()).add(passenger);
        }
        for (int i = 1; i < Constants.PORTS_EMBARKED_OPTIONS.length; i++){
            statisticsInfo += "Passengers who embarked on port " + Constants.PORTS_EMBARKED_OPTIONS[i] + ": " +
                    (float)passengersByPort.get(Constants.PORTS_EMBARKED_OPTIONS[i].charAt(0)).stream()
                            .filter(Passenger::isSurvived).count() / passengersByPort.get(Constants.PORTS_EMBARKED_OPTIONS[i].charAt(0)).size() * 100 + "% survived\n";
        }

        File statistics = new File("titanic-ex-master/src/data/statistics.txt");
        try {
            FileWriter fileWriter = new FileWriter(statistics);
            fileWriter.write(statisticsInfo);
            fileWriter.close();
        }catch (Exception ignored){}
    }
    private String statisticsByParameter(String parameter){
        int index = -1;
        for (int i = 0; true; i++){
            if (parameter.equals(Constants.STATISTICS_PARAMETER_OPTIONS[i])){
                index = i;
                break;
            }
        }
        StringBuilder statistics = new StringBuilder();
        List<String> parameterOfPassengers = new ArrayList<>();
        switch (index){
            case 0 -> {
                parameterOfPassengers = passengers.stream()
                        .map(passenger -> String.valueOf(passenger.getPClass())).toList();
            }
            case 1 -> {
                parameterOfPassengers = passengers.stream()
                        .map(passenger -> String.valueOf(passenger.getSex())).toList();
            }
            case 2 -> {
                parameterOfPassengers = passengers.stream()
                        .map(passenger -> String.valueOf(passenger.getAge()))
                        .map(age -> String.valueOf(
                                Double.parseDouble(age) <= 10? 0:((int)Double.parseDouble(age) / Constants.AGE_GROUP_DIFFERENCE)
                        ))
                        .map(age -> (Integer.parseInt(age) * 10 + 1 >= Constants.MAX_AGE_GROUP? Constants.MAX_AGE_GROUP + "+"
                                :(Integer.parseInt(age) > 0)?
                                (Integer.parseInt(age) * 10 + 1 + " - " + (Integer.parseInt(age) * 10 + 10)):
                                Integer.parseInt(age) * 10 + " - " + (Integer.parseInt(age) * 10 + 10)))
                        .map(age -> age.equals("0 - 9")? "0 - 10": age)
                        .toList();
            }
            case 3 -> {
                parameterOfPassengers = passengers.stream()
                        .map(passenger -> String.valueOf(passenger.getSibSP() + passenger.getParch() > 0)).toList();
            }
            case 4 -> {
                parameterOfPassengers = passengers.stream()
                        .map(passenger -> String.valueOf(passenger.getFare()))
                        .map(fare -> String.valueOf(
                                Double.parseDouble(fare) <= 10? 0:((int)Double.parseDouble(fare) / Constants.AGE_GROUP_DIFFERENCE)
                        ))
                        .map(fare -> String.valueOf(
                                Integer.parseInt(fare) > 1 && Integer.parseInt(fare) < Constants.MAX_FARE_GROUP/10? 1:fare)
                        )
                        .map(fare -> String.valueOf(
                                Integer.parseInt(fare) * 10 + 1 > Constants.MAX_FARE_GROUP? Constants.MAX_FARE_GROUP/10:fare)
                        )
                        .map(fare -> Integer.parseInt(fare) == 0 ? "0 - 10" :
                                Integer.parseInt(fare) == Constants.MAX_FARE_GROUP/10? Constants.MAX_FARE_GROUP + "+":Integer.parseInt(fare) * 10 + 1 + " - " + (Integer.parseInt(fare) * 10 + 10)
                        )
                        .toList();
            }
            case 5 -> {
                parameterOfPassengers = passengers.stream()
                        .map(passenger -> String.valueOf(passenger.getEmbarked())).toList();
            }
            default -> {
                return "";
            }
        }
        HashMap<String,Float> percentageOfParameter = new HashMap<>();
        for (String parameterOfPassenger: parameterOfPassengers){
            if (percentageOfParameter.containsKey(parameterOfPassenger)){
                percentageOfParameter.put(parameterOfPassenger,percentageOfParameter.get(parameterOfPassenger) + 1F);
            }
            else {
                percentageOfParameter.put(parameterOfPassenger, 1F);
            }
        }
        HashMap<Float,String> lineOfPercentage = new HashMap<>();
        for (String key: percentageOfParameter.keySet()){
            lineOfPercentage.put(percentageOfParameter.get(key),key + ": " + percentageOfParameter.get(key)/parameterOfPassengers.size() * 100 + "%\n");
        }
        Stack<Float> orderedPercentages = lineOfPercentage.keySet().stream().sorted().collect(Collectors.toCollection(Stack::new));
        List<Float> keys = new ArrayList<>();
        while (!orderedPercentages.empty()){
            keys.add(orderedPercentages.pop());
        }
        for (Float key: keys){
            statistics.append(lineOfPercentage.get(key));
        }
        return String.valueOf(statistics);
    }
    private int getIntFromString(String string){
        if (string.isEmpty()||string.equals(",")||string.equals(" ")){
            return 0;
        }
        else
            return Integer.parseInt(string);
    }
    private double getDoubleFromString(String string){
        if (string.isEmpty()||string.equals(",")){
            return 0;
        }
        else
            return Double.parseDouble(string);
    }
    private void initializeList(File file){
        passengers = new ArrayList<>();
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        fileReader.nextLine();
        while (fileReader.hasNext()){
            String line = fileReader.nextLine();
            StringBuilder idBuilder = new StringBuilder();
            int index = 0;
            while (line.charAt(index) != ','){
                idBuilder.append(line.charAt(index));
                index++;
            }
            int id = getIntFromString(String.valueOf(idBuilder));
            index += 1;
            boolean survived = line.charAt(index) == '1';
            index += 2;
            int pClass = getIntFromString(String.valueOf(line.charAt(index)));
            index += 3;
            StringBuilder nameBuilder = new StringBuilder();
            while (line.charAt(index) != '"'||line.charAt(index + 1) != ','){
                nameBuilder.append(line.charAt(index));
                index++;
            }
            String name = String.valueOf(nameBuilder);
            index += 2;
            StringBuilder sexBuilder = new StringBuilder();
            while (line.charAt(index) != ','){
                sexBuilder.append(line.charAt(index));
                index++;
            }
            String sex = String.valueOf(sexBuilder);
            index++;
            StringBuilder ageBuilder = new StringBuilder();
            while (line.charAt(index) != ','){
                ageBuilder.append(line.charAt(index));
                index++;
            }
            double age = getDoubleFromString(String.valueOf(ageBuilder));
            index++;
            int sibSp = getIntFromString(String.valueOf(line.charAt(index)));
            index += 2;
            int parch = getIntFromString(String.valueOf(line.charAt(index)));
            index += 2;
            StringBuilder ticketBuilder = new StringBuilder();
            while (line.charAt(index) != ','){
                ticketBuilder.append(line.charAt(index));
                index++;
            }
            String ticket = String.valueOf(ticketBuilder);
            index++;
            StringBuilder fareBuilder = new StringBuilder();
            while (line.charAt(index) != ','){
                fareBuilder.append(line.charAt(index));
                index++;
            }
            double fare = getDoubleFromString(String.valueOf(fareBuilder));
            index++;
            StringBuilder cabinBuilder = new StringBuilder();
            while (line.charAt(index) != ','){
                cabinBuilder.append(line.charAt(index));
                index++;
            }
            String cabin = String.valueOf(cabinBuilder);
            index++;
            char embarked = ' ';
            if (index < line.length()){
                embarked = line.charAt(index);
            }
            passengers.add(new Passenger(id,survived,pClass,name,sex,age,sibSp,parch,ticket,fare,cabin,embarked));
        }
    }
    private JLabel addLabel(String text){
        JLabel label = new JLabel(text);
        label.setBounds(x + Constants.MARGIN_FROM_LEFT, componentY, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(label);
        return label;
    }
    private JComboBox<String> addComboBox(Component adjacantComponent, String[] options){
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(adjacantComponent.getX() + adjacantComponent.getWidth() + Constants.MARGIN_FROM_LEFT, adjacantComponent.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(comboBox);
        return comboBox;
    }
    private JTextArea addTextArea(Component adjacantComponent){
        JTextArea textBox = new JTextArea();
        textBox.setBounds(adjacantComponent.getX() + adjacantComponent.getWidth() + Constants.MARGIN_FROM_LEFT, adjacantComponent.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(textBox);
        return textBox;
    }
    private JTextArea addTextArea(int x, int y){
        JTextArea textBox = new JTextArea();
        textBox.setBounds(x , y, Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(textBox);
        return textBox;
    }
    private JButton addButton(Component adjacantComponent,String text){
        JButton button = new JButton(text);
        button.setBounds(adjacantComponent.getX() + adjacantComponent.getWidth() + Constants.MARGIN_FROM_LEFT,
                adjacantComponent.getY(),Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
        this.add(button);
        return button;
    }
    private JButton addButton(int x, int y, String text){
        JButton button = new JButton(text);
        button.setBounds(x,y,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
        this.add(button);
        return button;
    }
    private JComboBox<String> addComboBox(int x, int y, String[] options){
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(x,y,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
        this.add(comboBox);
        return comboBox;
    }
}
