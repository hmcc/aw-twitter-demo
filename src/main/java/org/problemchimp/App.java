package org.problemchimp;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * Search for Aurora pictures from @aurorawatchuk and print the author, text,
 * image URL and location (if any)
 * 
 */
public class App {
	public static void main(String[] args) {

		try {
			Twitter twitter = TwitterFactory.getSingleton();
			Query query = new Query("@aurorawatchuk #aurora filter:links");
			query.setCount(500);
			QueryResult result = twitter.search(query);

			for (Status status : result.getTweets()) {
				System.out.print("@" + status.getUser().getScreenName() + ": ");
				
				for (MediaEntity entity : status.getMediaEntities()) {
					System.out.print(entity.getMediaURL());
				}
				
				if (status.getGeoLocation() != null) {
					System.out.println(status.getGeoLocation());
				} else if (status.isRetweet()
						&& status.getRetweetedStatus().getGeoLocation() != null) {
					System.out.println(status.getRetweetedStatus().getGeoLocation());
				} else {
					System.out.println();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
