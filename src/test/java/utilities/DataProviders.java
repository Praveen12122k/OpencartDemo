package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name="LoginData")
    public String[][] getdata() throws IOException {
      String path=".\\testData\\OpenCart_Logindata.xlsx";
      ExcelUtility xlutility= new ExcelUtility(path);
      int total_row=xlutility.getRowCount("Sheet1");
      int total_cell=xlutility.getCellCount("Sheet1",1);

      String logindata[][]= new String[total_row][total_cell];

      for (int i=1;i<=total_row;i++){
          for (int j=0;j<total_cell;j++){
           logindata[i-1][j]=xlutility.getCellData("Sheet1",i,j);
          }
      }
      return logindata;
    }
}
