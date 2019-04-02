/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    // Fill in here and change the return
      // Base Case. If no floors no trials can be done. If 1 floor only 1 trial can be done.
      if(floors == 0 || floors == 1)
      {
          return floors;
      }
      //Base Case Part 2: If only 1 sheet remaining must do trials for max number of floors.
      if(sheets == 1)
      {
          return floors;
      }

      //Need this to store number of trials for each iteration
      int numTrials = Integer.MAX_VALUE;

      for (int i = 1; i <=floors; i++) {
          // Case 1
          //int tempTrial = max(glassFallingRecur(i - 1, sheets - 1), glassFallingRecur(floors - i, sheets));
            int tempTrials = max(glassFallingRecur(i-1,sheets-1),glassFallingRecur(floors-i,sheets));
         // numTrials = min(tempTrial, numTrials);
           numTrials  = min(tempTrials,numTrials);
      }
      return numTrials + 1;

  }

  static int max(int a, int b)
  {
    return(a>b)? a:b;
  }
    static int min(int a, int b)
    {
        return(a<b)? a:b;
    }
  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors,int sheets) {
    // Fill in here and change the return
      if(floors == 0 || floors == 1)
      {
          return floors;
      }
      if(sheets == 1)
      {
          return floors;
      }

      int [][] glassMemTable = new int[sheets+1][floors+1];
      //Need to initialize all to a value I wont use.
      for (int i = 0; i <=floors ; i++) {
          for (int j = 0; j <= sheets; j++)
              glassMemTable[i][j] = -1;


      }

    return 0;
  }

  //public int glassFallngMemoHelp(int arr[][],int floors, int sheets);

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    // Fill in here and change the return]
      // Setting up table to store needed values
      int arrayTrials[][] = new int[sheets+1][floors+1];
    // Base Case If we have no floor no trials needed. If we have only 1 floor then only 1 trial required
      for (int i = 1; i <sheets ; i++)
      {
          arrayTrials[i][0] = 0;
          arrayTrials[i][1] = 1;

      }
      // Base Case 2: If there is only 1 sheet then we will need to do same amount of trials as there are floors.
      for (int i = 2; i <floors ; i++)
      {
          arrayTrials[1][i] = i;
      }

      // Checking each sheet with each floor
      for (int i = 2; i <=sheets; i++) {

          for (int j = 2; j <=floors ; j++) {

              arrayTrials[i][j]= Integer.MAX_VALUE;
              for (int k = 1; k<= j; k++) {

                  int tempTrials = 1 + max(arrayTrials[i-1][k-1],arrayTrials[i][j-k]);
                  arrayTrials[i][j] = min(tempTrials,arrayTrials[i][j]);

              }

          }

      }
return  arrayTrials[sheets][floors];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Recursion with 100,3 takes long
      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27,2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27,2);
      int minTrials2Bootm = gf.glassFallingRecur(100,3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);


      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Bottom + " " + minTrials2Bottom);
      //System.out.println("N/A" + " " + minTrials2Bottom);


  }
}
