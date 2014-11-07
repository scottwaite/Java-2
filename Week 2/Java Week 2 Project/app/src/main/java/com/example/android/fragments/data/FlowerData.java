package com.example.android.fragments.data;

import com.example.android.fragments.R;

import java.util.ArrayList;
import java.util.List;

public class FlowerData {

	private List<Flower> flowers = new ArrayList<Flower>();
	public List<Flower> getFlowers() {
		return flowers;
	}

	public FlowerData() {
		flowers.add(new Flower("Ouija", R.drawable.california_snow, 15.95,
				"Rating: 86%"));
		flowers.add(new Flower("Nightcrawler", R.drawable.princess_flower, 12.95,
				"Rating: 64%"));
		flowers.add(new Flower("Fury", R.drawable.haight_ashbury, 17.95,
				"Rating: 26%"));
	}


}
