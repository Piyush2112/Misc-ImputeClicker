

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class readFromSite {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> al_searchQuery = new ArrayList<String>();
		ArrayList<String> al_linkText= new ArrayList<String>();
		String searchForText="";

		try {
			// fetch the search query from the impute website
			URL searchQueryurl = new URL("http://www.impute.co.in/uploads/3/2/0/0/3200436/search_query.txt");
			BufferedReader searchQueryurlin = new BufferedReader(new InputStreamReader(searchQueryurl.openStream()));
			String searchQuerystr;
			while ((searchQuerystr = searchQueryurlin.readLine()) != null) {
				al_searchQuery.add(searchQuerystr);
				System.out.println(searchQuerystr);
			}
			searchQueryurlin.close();


			// fetch the linked text
			URL linkTexturl = new URL("http://www.impute.co.in/uploads/3/2/0/0/3200436/link_text.txt");
			BufferedReader linktextin = new BufferedReader(new InputStreamReader(linkTexturl.openStream()));
			String linkTextstr;
			while ((linkTextstr = linktextin.readLine()) != null) {
				al_linkText.add(linkTextstr);
				System.out.println(linkTextstr);
			}
			linktextin.close();
			// search for text from impute website
			URL searchForurl = new URL("http://www.impute.co.in/uploads/3/2/0/0/3200436/search_for.txt");
			BufferedReader searchFortextin = new BufferedReader(new InputStreamReader(searchForurl.openStream()));
			String searchForTextstr;
			while ((searchForTextstr = searchFortextin.readLine()) != null) {
				searchForText.concat(searchForTextstr);
				System.out.println(searchForTextstr);

				System.out.println(searchForText);
			}
			searchFortextin.close();

		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}

	}

}
