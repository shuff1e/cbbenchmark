package org.cbbenchmark;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Benchmark {

    public static String value = "78b6crtqefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшыисвртчой09йоцшущпыивруцтофчвтфыаовуралорпаг13655ке7134е5к784епув87йеа73пй37кугвцфорвч7гуряыш27164793еу78цвеа978уцесп78уцуеавпс7нцупвыфв7па78йуепывнфчгяспам8фынгавс78феп78цевп78йец78веый87цевы8пч6йцефыпавч678b6crtqefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшыисвртчой09йоцшущпыивруцтофчвтфыаовуралорпаг13655ке7134е5к784епув87йеа73пй37кугвцфорвч7гуряыш27164793еу78цвеа978уцесп78уцуеавпс7нцупвыфв7па78йуепывнфчгяспам8фынгавс78феп78цевп78йец78веый87цевы8пч6йцефыпавч678b6crtqefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшыисвртчой09йоцшущпыивруцтофчвтфыаовуралорпаг13655ке7134е5к784епув87йеа73пй37кугвцфорвч7гуряыш27164793еу78цвеа978уцесп78уцуеавпс7нцупвыфв7па78йуепывнфчгяспам8фынгавс78феп78цевп78йец78веый87цевы8пч6йцефыпавч6";

    /**
     * @param args -> num of keys, sleep time, num threads, hostname, isGenerator, timeout for read request
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        final int numKeys = Integer.valueOf(args[0]);
        final int sleepTime = Integer.valueOf(args[1]);
        final int numThreads = Integer.valueOf(args[2]);
        final String hostName = args[3];
        final boolean isGenerator = Boolean.valueOf(args[4]);
        final long timeout = Long.valueOf(args[5]);

        final int keys_per_thread = numKeys / numThreads;

        final ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        final MetricRegistry registry = new MetricRegistry();
        registry.timer("timer");
        registry.counter("success");
        registry.counter("total");
        registry.counter("timeout");

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(30, TimeUnit.SECONDS);

        if (isGenerator) {
            for (int i = 0; i < numThreads; i++) {
                final Callable<Future> worker = new Wgenerator(i * keys_per_thread, i * keys_per_thread + keys_per_thread, sleepTime, value, hostName);
                executor.submit(worker);
            }
        } else {
            for (int i = 0; i < numThreads; i++) {
                final Callable<Future> worker = new Wbenchmark(i * keys_per_thread, i * keys_per_thread + keys_per_thread, sleepTime, value, hostName, timeout, registry);
                executor.submit(worker);
            }
        }

        executor.shutdown();
        System.out.println("Done");
    }
}