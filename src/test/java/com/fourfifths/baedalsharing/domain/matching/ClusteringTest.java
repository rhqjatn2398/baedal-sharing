package com.fourfifths.baedalsharing.domain.matching;

import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.InstanceTools;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;


@Slf4j
@SpringBootTest
class ClusteringTest {
    Clustering clustering;

    @Autowired
    public ClusteringTest(Clustering clustering) {
        this.clustering = clustering;
    }

    @Test
    void cluster() {
        Random random = new Random();
        Dataset dataset = new DefaultDataset();
        int n = 16;
        for (int i = 0; i < n; i++) {
            Instance instance = InstanceTools.randomInstance(3);
            dataset.add(instance);
        }
        Dataset[] datasets = clustering.cluster(n / 4, dataset);
        for (Dataset ds : datasets) {
            log.info(ds.toString());
        }
    }
}