package me.everything.metrics.snapshots;

import java.util.HashMap;
import java.util.Map;

import com.codahale.metrics.Meter;

public class MeterSnapshot extends MetricSnapshot {

	public static final String MetricType = "Meter";
	
	private final long count;		
	private final double rateMean, rate1min, rate5min, rate15min;	
	private final String rateUnit;

	public MeterSnapshot(String name, Meter meter, String rateUnit, double rateFactor) {
		super(name);

		this.rateUnit = rateUnit;
		
		count = meter.getCount();
		
		rateMean = rateFactor * meter.getMeanRate();
		rate1min = rateFactor * meter.getOneMinuteRate();
		rate5min = rateFactor * meter.getFiveMinuteRate();
		rate15min = rateFactor * meter.getFifteenMinuteRate();	
	}

	@Override
	public String metricType() {
		return MetricType;
	}

	@Override
	public long count() {
		return count;
	}

	public double rateMean() {
		return rateMean;
	}

	public double rate1min() {
		return rate1min;
	}

	public double rate5min() {
		return rate5min;
	}

	public double rate15min() {
		return rate15min;
	}

	public String rateUnit() {
		return rateUnit;
	}
	
	@Override
	public Map<String, Double> allValues() {
		Map<String, Double> values = new HashMap<String, Double>();
		values.put("count", (double)count());
		values.put("rateMean", rateMean());
		values.put("rate1min", rate1min());
		values.put("rate5min", rate5min());
		values.put("rate15min", rate15min());
		return values;
	}
	
	@Override
	public Map<String, Double> barValues() {
		Map<String, Double> values = new HashMap<String, Double>();
		values.put("count", (double)count());
		return values;
	}

}
