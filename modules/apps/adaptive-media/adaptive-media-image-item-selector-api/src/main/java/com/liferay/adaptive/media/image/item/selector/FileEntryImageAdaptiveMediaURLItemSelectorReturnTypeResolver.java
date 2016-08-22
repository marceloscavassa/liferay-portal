/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.adaptive.media.image.item.selector;

import com.liferay.adaptive.media.AdaptiveMedia;
import com.liferay.adaptive.media.image.finder.ImageAdaptiveMediaFinder;
import com.liferay.adaptive.media.image.processor.ImageAdaptiveMediaAttribute;
import com.liferay.adaptive.media.image.processor.ImageAdaptiveMediaProcessor;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.item.selector.ItemSelectorReturnTypeResolver;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true, property = {"service.ranking:Integer=100"},
	service = ItemSelectorReturnTypeResolver.class
)
public class FileEntryImageAdaptiveMediaURLItemSelectorReturnTypeResolver
	implements ItemSelectorReturnTypeResolver
		<ImageAdaptiveMediaURLItemSelectorReturnType, FileEntry> {

	@Override
	public Class<ImageAdaptiveMediaURLItemSelectorReturnType>
		getItemSelectorReturnTypeClass() {

		return ImageAdaptiveMediaURLItemSelectorReturnType.class;
	}

	@Override
	public Class<FileEntry> getModelClass() {
		return FileEntry.class;
	}

	@Override
	public String getValue(FileEntry fileEntry, ThemeDisplay themeDisplay)
		throws Exception {

		JSONObject fileEntryJSONObject = JSONFactoryUtil.createJSONObject();

		String previewURL = DLUtil.getPreviewURL(
			fileEntry, fileEntry.getFileVersion(), themeDisplay,
			StringPool.BLANK, false, false);

		fileEntryJSONObject.put("defaultSource", previewURL);

		JSONArray sourcesArray = JSONFactoryUtil.createJSONArray();

		Stream<AdaptiveMedia<ImageAdaptiveMediaProcessor>> stream =
			_finder.getAdaptiveMedia(
				queryBuilder ->
					queryBuilder.allForFileEntry(fileEntry).
					orderBy(ImageAdaptiveMediaAttribute.IMAGE_WIDTH, true).
					done());

		List<AdaptiveMedia<ImageAdaptiveMediaProcessor>> adaptiveMediaList =
			stream.collect(Collectors.toList());

		AdaptiveMedia previousAdaptiveMedia = null;

		for (AdaptiveMedia<ImageAdaptiveMediaProcessor> adaptiveMedia :
				adaptiveMediaList) {

			JSONObject sourceJSONObject = getSourceJSONObject(
				adaptiveMedia, previousAdaptiveMedia);

			sourcesArray.put(sourceJSONObject);

			previousAdaptiveMedia = adaptiveMedia;
		}

		fileEntryJSONObject.put("sources", sourcesArray);

		return fileEntryJSONObject.toString();
	}

	private JSONObject getSourceJSONObject(
		AdaptiveMedia<ImageAdaptiveMediaProcessor> adaptiveMedia,
		AdaptiveMedia<ImageAdaptiveMediaProcessor> previousAdaptiveMedia) {

		Optional<Integer> widthOptional = adaptiveMedia.getAttributeValue(
			ImageAdaptiveMediaAttribute.IMAGE_WIDTH);

		JSONObject sourceJSONObject = JSONFactoryUtil.createJSONObject();

		sourceJSONObject.put("src", adaptiveMedia.getURI());

		JSONArray attributesJSONArray = JSONFactoryUtil.createJSONArray();

		widthOptional.ifPresent(
			width -> {
				JSONObject maxWidthJSONObject =
					JSONFactoryUtil.createJSONObject();

				maxWidthJSONObject.put("key", "max-width");
				maxWidthJSONObject.put("value", width + "px");

				attributesJSONArray.put(maxWidthJSONObject);

				if (previousAdaptiveMedia != null) {
					Optional<Integer> previousWidthOptional =
						previousAdaptiveMedia.getAttributeValue(
							ImageAdaptiveMediaAttribute.IMAGE_WIDTH);

					previousWidthOptional.ifPresent(
						previousWidth -> {
							JSONObject minWidthJSONObject =
								JSONFactoryUtil.createJSONObject();

							minWidthJSONObject.put("key", "min-width");
							minWidthJSONObject.put(
								"value", previousWidth + "px");

							attributesJSONArray.put(minWidthJSONObject);
						}
					);
				}
			}
		);

		return sourceJSONObject.put("attributes", attributesJSONArray);
	}

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.repository.model.FileVersion)"
	)
	private ImageAdaptiveMediaFinder _finder;

}