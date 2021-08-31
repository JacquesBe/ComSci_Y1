package spectrogram;


public class DigitRecognition  {
   
    static final String[] FILES_ARR= {"0A.wav","0B.wav","1A.wav","1B.wav","2A.wav","2B.wav","3A.wav","3B.wav","4A.wav","4B.wav","5A.wav",
                                      "5B.wav","6A.wav","6B.wav","7A.wav","7B.wav","8A.wav","8B.wav","9A.wav","9B.wav"};
    static final Wave[] waves = new Wave[20];
    
    public static double[] compressData(String file){
        double sum = 0;
        double[] spoints = new double[9]; 
        Wave waves = new Wave(file); 
        double[][] normdata = new Spectrogram(waves).getNormalizedSpectrogramData();
        int l = (int)(normdata.length/9);
        int L = l;
        for (int j = 0; j < normdata.length; j ++){
            for (int k = 0; k < normdata[0].length; k++){

                int x = (int) (((double) j)*3/normdata.length);
                int xOff = (int) (((double) k)*3/normdata[0].length);

                spoints[x*3 + xOff] += normdata[j][k];
                // sum += normdata[j][k];
            }
            // if (j == l){
                
            //     spoints[l/L -1] = sum;
            //     l += L;
                
            // }
        }
       return spoints;
    }
    
    public static double[][] compileData(String[] files){
        int filecount = files.length;
        double[] cd = new double[9];
        double[][] fd = new double[filecount][9];
        for (int i = 0; i < files.length; i ++){
      
            String fil = files[i];
            cd = compressData(fil);
            for (int k = 0 ; k < 9; k ++){
                fd[i][k] = cd[k];
                
            }
            
        }
        return fd;
    }


    public static int recogniseNumber(String mystery, double[][] data){
      
       double sum = 0;
       int number;
        double[] mys = compressData(mystery);
        int[] num = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9};
        double[][] dif = new double[data.length][data[0].length];
        double[] avgdistance = new double[data.length];
        for (int i = 0; i < data.length; i ++){
           for (int j = 0 ; j< data[0].length; j ++){
                dif[i][j] = Math.abs(mys[j] - data[i][j]); 
                sum +=  Math.pow(mys[j] - data[i][j],2);
            }
           avgdistance[i] = Math.sqrt(sum);
           sum = 0; 
        }
        double mindist = Math.abs(avgdistance[0]);
        number = 0;
        for (int i = 1; i < avgdistance.length;i++){
            if (Math.abs(avgdistance[i]) < mindist){
                mindist = Math.abs(avgdistance[i]);
                number = i;
            }
        }
        
        return num[number];
    }


    public static void main(String[] args){
        double[][] points = compileData(FILES_ARR);
        System.out.println(recogniseNumber("mystery1.wav", points));
    }
    
}
