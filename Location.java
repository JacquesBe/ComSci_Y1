import java.util.Locale;


public class Location {
    private final int R = 6371;
    private double lati;
    private double longi;
    private final String[] cities = {"Cape Town", "New York", "London", "Shanghai", "Tokyo"};
    private final double[][] coords = {{-33.9253,18.4239},{40.7127,-74.0059},{51.5074,-0.1278},{31.2000,121.5000},{35.6762,139.6503}};
    
    public Location(String city){
        for (int i = 0; i < cities.length; i++){
            if (cities[i].equals(city)){
                lati = coords[i][0];
                longi = coords[i][1];
            }
        }
    }
    
    public Location(double lat, double lon){
        lati = lat;
        longi = lon;
    }
    
    public double distanceTo(Location l){  
     final double a = Math.pow(Math.sin(Math.toRadians((l.getLatitude() - this.lati)/2)),2) + Math.cos(Math.toRadians(this.lati)) * 
     Math.cos(Math.toRadians(l.getLatitude()))
        * Math.pow(Math.sin(Math.toRadians((l.getLongitude()-this.longi)/2)),2);
        final double c = 2*Math.atan2(Math.toRadians(Math.sqrt(a)), Math.toRadians(Math.sqrt(1-a)));
        final double d = R*c;
        return d;
    }
    public static Location random(){
        final double lon;
        final double lat;
        double x = Math.random();
        if (x > 0.5){
            lat = x*90;
        }else 
            {lat = x*90*-1;}
         x = Math.random();
        if (x > 0.5){
            lon = x*180;
        }else 
            {lon = x*180*-1;}
        return new Location(lat,lon);
    }

    public static Location parse(String s){
        final double lat;
        final double lon;
        
        if (s.indexOf('S') != -1){
        
            lat = Double.parseDouble(s.substring(s.indexOf('S') + 1, s.indexOf(','))) * -1;
        }else{
            lat = Double.parseDouble(s.substring(s.indexOf('N') + 1, s.indexOf(',')));
          
        }
        s = s.substring(s.indexOf(' ') + 1);
        if (s.indexOf('W') != -1){
         
            lon = Double.parseDouble(s.substring(s.indexOf('W') + 1)) * -1;
        }else{
            lon = Double.parseDouble(s.substring(s.indexOf('E') + 1)); 
        }
        
        
        return new Location(lat,lon);

    }

    public double getLatitude(){
        return lati;
    }
    public double getLongitude(){
        return longi;
    }

    public String toString(){
        String slati = "N";
        String slongi = "E";

        if (this.lati <0){
            slati = "S";
        }
        if (this.longi <0){
            slongi = "W";
        }

        return String.format(Locale.US,"%.4f", Math.abs(this.lati)) + " " + slati + 
        ", " + String.format(Locale.US,"%.4f", Math.abs(this.longi)) + " " + slongi;
    }

    public static void main(String[] args){
        Location a = Location.parse("N20, W30");
       StdOut.print(a.toString());
        
    }
    
}
