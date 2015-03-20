package GarageSim;

/**
 * A class to represent the simulation of an FIU garage where the maximum
 * capacity is 7 cars and there is a waiting line for cars wanting to park in
 * the garage; cars from the garage may have to be removed from the North end to
 * retrieve a departing car
 */
public class GarageSimulation {

    Deque garage;                           //garage where cars will be parked
    LinkedList waitingLine;                 //waiting line 
    Stack carPosition;                      //saved position of temporarily
                                            //moved cars
    private final int GARAGE_CAPACITY = 7;  //maximum capacity of garage

    public GarageSimulation() {
        //constructor for Garage Simulation
        garage = new Deque();
        waitingLine = new LinkedList();
        carPosition = new Stack();

    }

    /**
     * Finds whether garage is full or not
     *
     * @return true or false
     */
    public boolean garageIsFull() {

        return garage.length == GARAGE_CAPACITY;

    }

    /**
     * Finds whether waiting line is empty or not
     *
     * @return true or false
     */
    public boolean waitingLineIsEmpty() {

        return waitingLine.length <= 0;

    }

    /**
     * Moves waiting cars (in order) to garage if it isn't full
     */
    public void checkWaitingLine() {

        garage.setIterator();
        //while there are more waiting cars to be moved...
        while ((garageIsFull() == false) && (waitingLineIsEmpty() == false)) {
            System.out.println("***" + obtainLicensePlate(waitingLine.head)
                    + " has moved from the waiting line and is now #"
                    + (garage.length + 1) + " in the garage");
            //move longest waiting car from waiting line to garage
            garage.appendEndNode(waitingLine.removeFirstNode());

            System.out.println(toString());

        }
    }

    private String obtainLicensePlate(Node car) {
        //obtains license plate of car
        String licensePlate = (String) car.data;
        return licensePlate;

    }

    /**
     * Addresses cars that has arrive to FIU -- the simulation will either let
     * the car park into the garage if it is not full or put the car in the
     * waiting line
     *
     * @param car car to be parked
     */
    public void serveArrival(Node car) {

        String licensePlate = obtainLicensePlate(car);
        //if garage has room...
        if (garageIsFull() == false) {
            //..move car into garage
            garage.appendEndNode(car);
            System.out.println("***" + licensePlate + " has arrived at FIU "
                    + "and is #" + garage.length + " in the garage.");
        } //otherwise, move the car to the waiting line
        else {
            waitingLine.appendNode(car);
            System.out.println("***" + licensePlate + " has arrived at FIU, "
                    + "but the garage is full! " + licensePlate + " is "
                    + "now #" + waitingLine.length + " in the waiting line.");
        }

    }

    /**
     * Attempts to depart car with specific license plate -- the simulation will
     * either take the car out from the garage if it's in there, remove the car
     * from the waiting line if it's present in the line or say that the car is
     * in either
     *
     * @param car car to be removed from garage or waiting line
     */
    public void serveDeparture(Node car) {
        System.out.println("***A request to obtain "
                + obtainLicensePlate(car) + " has been made.");
            //CAR DEPARTURE FROM FIU GARAGE
        //*****************************************************************
        if (garage.contains(car)) {
            garage.setIterator();
            garage.next();
            //while not yet at car... remove all cars in front of it and 
            //save position
            while (!(garage.iterator.equals(car))) {
                garage.head.extra++;    //icrements the # of times a specific 
                                        //car has moved out of the garage
                System.out.println("***" + obtainLicensePlate(garage.head)
                        + " has been temporarily moved out of the garage to let "
                        + obtainLicensePlate(car) + " depart from the garage.");
                //temporarily take out cars from garage to outside in correct
                //order to move out the next car
                garage.next();
                carPosition.push(garage.removeNode());
            }

            garage.head.extra++;        //increments the # of times the departed
                                        //car has moved out of the garage
            System.out.println("***" + obtainLicensePlate(garage.head) + " has "
                    + "departed from the FIU garage. It has been moved out "
                    + "of the garage " + garage.head.extra + " time(s).");
            garage.removeNode();        //car departs from FIU garage

            //while there are still cars to be moved back in the garage...
            while (!(carPosition.isEmpty())) {

                //take moved cars back into garage with correct order
                System.out.println("***" + obtainLicensePlate((Node) carPosition.peek())
                        + " has been moved back into the garage.");
                garage.appendFirstNode((Node) carPosition.pop());
            }
            //CAR DEPARTURE FROM WAITING LINE
            //*****************************************************************
        } else if (waitingLine.contains(car)) {

            Node n = waitingLine.removeNode(car);    //car departs from 
                                                     //waiting line
            System.out.println("***" + obtainLicensePlate(car) + " has departed FIU "
                    + "from the waiting line. It never made it to the garage.");
            //CAR NOT FOUND IN GARAGE NOR WAITING LINE
            //*****************************************************************
        } else {

            System.out.println("***" + obtainLicensePlate(car) + " was neither found in "
                    + "the garage or the waiting line! Try another garage! (No "
                    + "changes made)");

        }

    }

    /**
     * Analyzes what to do with the information provided to the simulation -- a
     * car will either arrive or attempt to be departed
     *
     * @param status arrival or departure
     * @param licensePlate license plate number of car
     */
    public void interpretCar(String status, String licensePlate) {

        String data = licensePlate;

        Node car = new Node(data);
        //if car arrives, serve it
        if (status.equals("A")) {
            serveArrival(car);
        } //otherwise, attempt to depart the car
        else {
            serveDeparture(car);
        }

    }

    /**
     * Shows the status of the garage and waiting line in the Garage Simulation
     * by showing the cars (in order) parked in each respective place
     *
     * @return string of information/data about the running Garage Simulation
     */
    @Override
    public String toString() {

        String output = "=====================================================================================================\n"
                + "Status of Garage (cars from 1st to last position):\n"
                + "=====================================================================================================\n";

        if (garage.length == 0) {
            output = output + "No cars currently in garage.\n";
        } else {
            int i = 1;
            Node pointer = garage.head;
            while (i <= garage.length) {
                output = output + "#" + i + " - " + pointer.data + "    ";
                i++;
                pointer = pointer.next;
            }
            output = output + "\n";
        }

        output = output + "=====================================================================================================\n"
                + "Status of Waiting Line (cars from 1st to last position):\n"
                + "=====================================================================================================\n";

        if (waitingLine.length == 0) {
            output = output + "No cars currently in waiting line.\n";
        } else {
            int i = 1;
            Node pointer = waitingLine.head;
            while (i <= waitingLine.length) {
                output = output + "#" + i + " - " + pointer.data + "    ";
                i++;
                pointer = pointer.next;
            }
            output = output + "\n";
        }

        return output;
    }

}
