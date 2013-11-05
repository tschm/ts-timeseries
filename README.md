# ts-cta

A lean Java framework for the rapid prototyping of quantitative trading strategies. The strategies I have in mind are 
time-driven, e.g. there is a defined schedule of trade times. Such strategies are very common and in particular
CTAs tend to trade based on fixed schedules, e.g. trade at 2.04pm every day, etc.

A strategy is a process iterating over all points in this schedule. Before and after this iteration the user may
specify certain actions taking place (e.g. loading a license for external software).

All parameters are injected using a configuration file. A rather complex builder is then parsing this file and loading all
data from sources specified in this configuration file.

The strategy also provides a broker. The actual implementation of the strategy has to dump desired positions to this broker.
The broker is storing this information and will later provide a simple (hopefully positive) profit stream.



## Motivation

* Most tasks in a fund should be unified (less error-prone and less time-consuming).
* A researcher should not be concerned about database management or performance analysis.
* Hides complexity for a researcher to do actual research rather than development.

## The strategy interface

```java
package com.ts.timeseries.strategy;

import com.ts.timeseries.input.future.Future;
import com.ts.timeseries.matrix.Matrix;
import com.ts.timeseries.properties.Configuration;


public interface Strategy {
    // configuration describing the parameter set
    Configuration config();
    // set of time stamps the strategy is iterating over
    SortedSet<Long> timegrid();
    // time series data
    Map<String, Matrix> data();
    // broker for post-trade analysis
    Broker broker();
    // Map of symbols -> future
    Map<String, Future> futures();
}
```

## Configuration example
```
tolerance.price=18h
simulation.time=20110101
portfolio=AUD,CAD,COPPER

data.universe=data//assets.csv
data.series.csv=data//priceData.csv
data.series.csv.format=yyyyMMddHHmmss
data.source=CSV
trade.start.date=20030102 18:00

CAD.cv=10000
AUD.cv=20000
COPPER.cv=15000 
```

## Building a stragegy
```java
StrategyBuilder.build(configurationFile);
```

* Parse configuration file (symbols, parameters, classnames for reflection, ...)
* Access and prepare data (compute asset returns, align on grid, ...)
* Construct a broker for post-analysis

## The abstract runner
```java
package com.ts.timeseries.strategy;

public abstract class StrategyRunner {

    public Strategy strategy() {
        return strategy;
    }

    private final Strategy strategy;

    public StrategyRunner(Strategy strategy) {
        this.strategy = strategy;
    }

    public abstract void after();
    public abstract void before();
    public abstract void react(Long time);

    public Portfolio run()
    {
        this.before();
        for (Long time : strategy.timegrid())
            react(time);

        this.after();
        return strategy.broker().buildPortfolio(0);
    }
}
```
## Example
A complete example is available [here](http://tschm.github.io/ts-strat/).

## Matlab

By design this framework is a set of tools supporting the development of strategies within Matlab.
All tools are accessible via
```
system\libs\ts-cta-1.2-jar-with-dependencies.jar
```
To make sure this file is added to the dynamic java class path of Matlab please
run 
```
startHula.m 
```
in the folder system. This script appends the java class path
and creates the universe of available static asset data.




