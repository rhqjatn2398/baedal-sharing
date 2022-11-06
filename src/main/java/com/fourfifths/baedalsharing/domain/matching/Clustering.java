package com.fourfifths.baedalsharing.domain.matching;

import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import org.springframework.stereotype.Component;

@Component
public class Clustering {
    public Clustering() {
    }

    public Dataset[] cluster(int numClusters, Dataset dataset) {
        KMeans kMeans = new KMeans(numClusters);
        return kMeans.cluster(dataset);
    }


}
