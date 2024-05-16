package cabin;

import java.util.ArrayList;
import java.util.List;

public class Cabin {
    private List<String> seats;
    private int businessClassSeats;
    private int premiumEconomySeats;
    private int economySeats;

    public Cabin() {
        seats = generateSeats();
        calculateSeatCounts();
    }

    private List<String> generateSeats() {
        List<String> seatList = new ArrayList<>();

        // Business Class: Reihen 1-8
        for (int row = 1; row <= 8; row++) {
            for (char seat = 'A'; seat <= 'K'; seat++) {
                if (seat != 'B' && seat != 'E' && seat != 'F' && seat != 'J' && seat != 'I') {
                    seatList.add(row + String.valueOf(seat));
                }
            }
        }

        // Premium Economy: Reihen 12, 14 und 15
        int[] premiumEconomyRows = {12, 14, 15};
        for (int row : premiumEconomyRows) {
            for (char seat = 'A'; seat <= 'K'; seat++) {
                if (seat != 'B'  && seat != 'F' && seat != 'I' && seat != 'J') {
                    seatList.add(row + String.valueOf(seat));
                }
            }
        }

        // Economy: Reihe 16
        for (char seat = 'A'; seat <= 'G'; seat++) {
            if (seat != 'B'  && seat != 'F') {
                seatList.add(16 + String.valueOf(seat));
            }
        }

        // Economy: Reihen 18-26
        for (int row = 18; row <= 26; row++) {
            for (char seat = 'A'; seat <= 'K'; seat++) {
                if ( seat != 'F' && seat != 'I') {
                    seatList.add(row + String.valueOf(seat));
                }
            }
        }

        // Economy: Reihe 27
        for (char seat = 'B'; seat <= 'J'; seat++) {
            if (seat != 'E' && seat != 'F' && seat != 'I') {
                seatList.add(27 + String.valueOf(seat));
            }
        }

        // Economy: Reihen 28-41
        for (int row = 28; row <= 41; row++) {
            for (char seat = 'A'; seat <= 'K'; seat++) {
                if (seat != 'F' && seat != 'I') {
                    seatList.add(row + String.valueOf(seat));
                }
            }
        }

        // Economy: Reihe 42
        for (char seat = 'A'; seat <= 'G'; seat++) {
            if (seat != 'B'  && seat != 'F') {
                seatList.add(42 + String.valueOf(seat));
            }
        }

        return seatList;
    }



    private void calculateSeatCounts() {
        // Berechnung der Anzahl der Sitze für jede Klasse
        businessClassSeats = 48;
        premiumEconomySeats = 21;
        economySeats = 5 + 81 + 7 + 126 + 5; ;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cabin:").append("\n");
        sb.append("BUSINESS CLASS: ").append("1,2,3,4,5,6,7,8 [A,C,D,G,H,K] : ").append(businessClassSeats).append("\n");
        sb.append("PREMIUM_ECONOMY: ").append("12,14,15 [A,C,D,E,G,H,K] : ").append(premiumEconomySeats).append("\n");
        sb.append("ECONOMY: ").append("16 [A,C,D,E,G] : ").append(5).append("\n");
        sb.append("18,19,20,21,22,23,24,25,26,27,28 [A,B,C,D,E,G,H,J,K] : ").append(81).append("\n");
        sb.append("27 [B,C,D,E,G,H,J] : ").append(7).append("\n");
        sb.append("28,29,30,31,32,33,34,35,36,37,38,39,40,41 [A,B,C,D,E,G,H,J,] : ").append(126).append("\n");
        sb.append("42 [A,C,D,E,G] : ").append(5).append("\n");
        sb.append(":").append(economySeats).append("\n");
        sb.append(":").append(businessClassSeats + premiumEconomySeats + economySeats ).append("\n");

        return sb.toString();
    }
}

