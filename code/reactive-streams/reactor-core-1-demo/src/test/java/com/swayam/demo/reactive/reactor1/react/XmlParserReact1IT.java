package com.swayam.demo.reactive.reactor1.react;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.zip.GZIPInputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swayam.demo.reactive.reactor1.model.LineItemRow;

import reactor.core.Environment;
import reactor.core.composable.Stream;
import reactor.event.dispatch.Dispatcher;
import reactor.event.dispatch.ThreadPoolExecutorDispatcher;

public class XmlParserReact1IT {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlParserReact1IT.class);

    @Test
    public void testParse() throws IOException, InterruptedException {

	Environment environment = new Environment();
	Dispatcher dispatcher = new ThreadPoolExecutorDispatcher(1, 128);
	environment.addDispatcher(ThreadPoolExecutorDispatcher.class.getSimpleName(), dispatcher);

	CountDownLatch countDownLatch = new CountDownLatch(1);

	XmlParserReact1 xmlParser = new XmlParserReact1(environment, countDownLatch);
	Stream<LineItemRow> stream = xmlParser.parse(new GZIPInputStream(XmlParserReact1IT.class.getResourceAsStream("/datasets/xml/www.cs.washington.edu/lineitem.xml.gz")));

	stream.consume((LineItemRow lineItemRow) -> {
	    LOGGER.info("new event {}", lineItemRow);
	});

	countDownLatch.await();

    }

}
