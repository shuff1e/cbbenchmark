package org.cbbenchmark;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Benchmark {

    public static String value = "78b6crtqefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец58foxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<BлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшыисвртчой09йоцшущпыивруцтофчвтфыаовуралорпаг13655ке7134е5к784епув87йеа73пй37кугвцфорвч7гуряыш27164793еу78цвеа978уцесп78уцуеавпс7нцупвыфв7па78йуепывнфчгяспам8фынгавс78феп78цевп78йец78веый87цевы8пч6йцефыпавч678b6crtqefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшыисвртчой09йоцшущпыивруцтофчвтфыаовуралорпаг13655ке7134е5к784епув87йеа73пй37кугвцфорвч7гуряыш27164793еу78цвеа978уцесп78уцуеавпс7нцупвыфв7па78йуепывнфчгяспам8фынгавс78феп78цевп78йец78веый87цевы8пч6йцефыпавч678b6crtqefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшыисвртчой09йоцшущпыивруцтофчвтфыаовуралорпаг13655ке7134е5к784епув87йеа73пй37кугвцфорвч7гуряыш27164793еу78цвеа978уцесп78уцуеавпс7нцупвыфв7па78йуепывнфчгяспам8фынгавс78феп78цевп78йец78веый87цевы8пч6йцефыпавраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакраоыпвфывмтосрчгнвакфвыйацныгукец589йргыщшцторвуырфалдоофвзйшefoxgausH<Bлоывивафмпайвимвпафровераоыпвфывмтосрчгнвакч6";

    /**
     * @param args -> hostname, bucket name, bucket password, num of keys, size of document value , timeout for read request, sleep time, num threads, ratio
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // System.out.println(value.getBytes().length);//8879

        final String hostName = args[0];
        final String bucketName = args[1];
        final String bucketPwd = args[2];
        final int numKeys = Integer.valueOf(args[3]);
        final int valueSize = Integer.valueOf(args[4]);
        final long timeout = Long.valueOf(args[5]);
        final int sleepTime = Integer.valueOf(args[6]);
        final int numThreads = Integer.valueOf(args[7]);
        final double ratio = Double.valueOf(args[8]);
        final String prefix = String.valueOf(args[9]);
        final int loopTimes = Integer.valueOf(args[10]);

        byte[] src = value.getBytes();
        byte[] dest = new byte[valueSize];
        System.arraycopy(src, 0, dest, 0, valueSize);
        value = new String(dest);

        final int readNumThreads = (int)Math.round(numThreads * ratio);
        final int writeNumThreads = numThreads - readNumThreads;
        final int read_keys_per_thread = (readNumThreads == 0)?0:numKeys / readNumThreads;
        final int write_keys_per_thread = (writeNumThreads == 0)?0:numKeys / writeNumThreads;

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

        for (int i = 0; i < readNumThreads; i++) {
            final Callable<Future> worker = new Wbenchmark(i * read_keys_per_thread, i * read_keys_per_thread + read_keys_per_thread, sleepTime, value, hostName, timeout, registry, bucketName, bucketPwd, prefix, loopTimes);
            executor.submit(worker);
        }
        for (int i = 0; i < writeNumThreads; i++) {
            final Callable<Future> worker = new Wgenerator(i * write_keys_per_thread, i * write_keys_per_thread + write_keys_per_thread, sleepTime, value, hostName, timeout, registry, bucketName, bucketPwd, prefix, loopTimes);
            executor.submit(worker);
        }

        executor.shutdown();
        System.out.println("Done");
    }
}
