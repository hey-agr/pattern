import downloader.YoutubeDownloader;
import proxy.YoutubeCacheProxy;
import some_cool_media_library.ThirdPartyYoutubeClass;

/**
 * Заместитель — это объект, который выступает прослойкой между клиентом и реальным сервисным объектом.
 * Заместитель получает вызовы от клиента,
 * выполняет свою функцию (контроль доступа, кеширование, изменение запроса и прочее),
 * а затем передаёт вызов сервисному объекту.
 *
 * Заместитель имеет тот же интерфейс, что и реальный объект,
 * поэтому для клиента нет разницы — работать через заместителя или напрямую.
 *
 * Применимость: Паттерн Заместитель применяется в Java коде тогда,
 * когда надо заменить настоящий объект его суррогатом, причём незаметно для клиентов настоящего объекта.
 * Это позволит выполнить какие-то добавочные поведения до или после основного поведения настоящего объекта.
 *
 * Признаки применения паттерна: Класс заместителя чаще всего делегирует всю настоящую работу своему реальному объекту.
 * Заместители часто сами следят за жизненным циклом своего реального объекта.
 *
 * Пример: Кеширующий заместитель
 * Этот пример показывает как с помощью Заместителя можно сделать более эффективной коммуникацию по сети
 * с внешним видеосервисом, кешируя повторяющиеся запросы.
 * Заместитель особенно полезен, если у вас нет доступа к коду служебных классов,
 * поведение которых хочется улучшить. Он позволяет изменить поведение реального объекта,
 * прозрачно для клиентского кода. В этом примере заместитель и сам реальный объект взаимозаменяемы.
 */
public class Main {
    public static void main(String[] args) {
        YoutubeDownloader naiveDownloader = new YoutubeDownloader(new ThirdPartyYoutubeClass());
        YoutubeDownloader smartDownloader = new YoutubeDownloader(new YoutubeCacheProxy());

        long naive = test(naiveDownloader);
        long smart = test(smartDownloader);
        System.out.print("Time saved by caching proxy: " + (naive - smart) + "ms");
    }

    private static long test(YoutubeDownloader downloader) {
        long startTime = System.currentTimeMillis();

        // User behavior in our app:
        downloader.renderPopularVideos();
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderPopularVideos();
        downloader.renderVideoPage("dancesvideoo");
        // Users might visit the same page quite often.
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderVideoPage("someothervid");

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.print("Time elapsed: " + estimatedTime + "ms\n");
        return estimatedTime;
    }
}
