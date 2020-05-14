package controller;
import model.*;
import controller.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchingManagerTests {

    private SearchingManager searchingManager = new SearchingManager();
    private Storage storage = Storage.getStorage();
    private FileSaver fileSaver = new FileSaver(storage);

    @Test
    public void getCurrentFiltersTest() throws Exception {
        Filter filter1 = new Filter("category","housing");
        Filter filter2 = new Filter("category","food");
        Filter filter3 = new Filter("price","20");
        Filter filter4 = new Filter("name","soon");
        ArrayList<Filter> expected = new ArrayList<>();
       // expected.add(filter1);
       //expected.add(filter2);
        expected.add(filter3);
        expected.add(filter4);
        //searchingManager.performFilter("category","housing");
        //searchingManager.performFilter("category","food");
        searchingManager.performFilter("price","20");
        searchingManager.performFilter("name","soon");
        Assert.assertTrue(expected.get(0).equals(searchingManager.getCurrentFilters().get(0)));
        Assert.assertTrue(expected.get(1).equals(searchingManager.getCurrentFilters().get(1)));
        try {
            searchingManager.performFilter("name","soon");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"This filter is already selected!");
        }
        searchingManager.disableFilter("name","soon");
        Assert.assertTrue(expected.get(0).equals(searchingManager.getCurrentFilters().get(0)));
        Assert.assertEquals(searchingManager.getCurrentFilters().size(),1);
        try {
            searchingManager.disableFilter("name","soon");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"You did not select this filter");
        }
    }

    @Test
    public void getCurrentSortsTest() throws Exception {
        Sort sort1 = new Sort("price");
        Sort sort2 = new Sort("average rate");
        ArrayList<Sort> expected = new ArrayList<>();
        expected.add(sort1);
        expected.add(sort2);
        searchingManager.performSort("price");
        searchingManager.performSort("average rate");
        Assert.assertTrue(expected.get(0).equals(searchingManager.getCurrentSorts().get(0)));
        Assert.assertTrue(expected.get(1).equals(searchingManager.getCurrentSorts().get(1)));
        try {
            searchingManager.performSort("price");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"This sort is already selected!");
        }
        searchingManager.disableSort("average rate");
        Assert.assertTrue(expected.get(0).equals(searchingManager.getCurrentSorts().get(0)));
        Assert.assertEquals(searchingManager.getCurrentSorts().size(),1);
        try {
            searchingManager.disableSort("average rate");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"You did not select this sort");
        }
    }

}
