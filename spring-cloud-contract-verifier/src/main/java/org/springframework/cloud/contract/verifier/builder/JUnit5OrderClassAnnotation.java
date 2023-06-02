/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.contract.verifier.builder;

import java.util.Arrays;

import org.springframework.cloud.contract.verifier.config.TestFramework;

class JUnit5OrderClassAnnotation implements ClassAnnotation {

	private final BlockBuilder blockBuilder;

	private final GeneratedClassMetaData generatedClassMetaData;

	private static final String[] ANNOTATIONS = {"@TestMethodOrder(MethodOrderer.Alphanumeric.class)"};

	JUnit5OrderClassAnnotation(BlockBuilder blockBuilder, GeneratedClassMetaData generatedClassMetaData) {
		this.blockBuilder = blockBuilder;
		this.generatedClassMetaData = generatedClassMetaData;
	}

	@Override
	public ClassAnnotation call() {
		Arrays.stream(ANNOTATIONS).forEach(this.blockBuilder::addIndented);
		return this;
	}

	@Override
	public boolean accept() {
		return this.generatedClassMetaData.configProperties.getTestFramework() == TestFramework.JUNIT5
	&& this.generatedClassMetaData.listOfFiles.stream().anyMatch(meta -> meta.getOrder() != null);
	}

}
