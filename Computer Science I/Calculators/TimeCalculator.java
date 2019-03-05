import java.util.*;
class TimeCalculator{
  public static void main(String[] args){
    final int min = 60;
    final int hour = 3600;
    final int day = 86400;
    int days = 0;
    int hours = 0;
    int mins = 0;
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter number of seconds: ");
    int seconds = scan.nextInt();
    final int sec = seconds;
    for(;seconds>min;seconds-=min){
      mins++;
      for(;seconds>hour;seconds-=hour){
        hours++;
        for(;seconds > day;seconds-=day){
          days++;
        }
      }
    }
    if(days>0){
      System.out.println(sec+ " seconds is equal to "+days+" day(s), "+hours+" hour(s), "+mins+ " minute(s), "+seconds+" second(s).");
    }
    else if(hours>0){
      System.out.println(sec+ " seconds is equal to "+hours+" hour(s), "+mins+ " minute(s), "+seconds+" second(s).");
    }
    else if (mins>0){
      System.out.println(sec+ " seconds is equal to "+mins+ " minute(s), "+seconds+" second(s).");
    }
    else{
      System.out.println(sec+ " seconds is equal to "+seconds+" second(s).");
    }
  }
}
