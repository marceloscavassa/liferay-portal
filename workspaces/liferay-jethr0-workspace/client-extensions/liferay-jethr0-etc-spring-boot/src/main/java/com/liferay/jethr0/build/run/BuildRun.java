/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.build.run;

import com.liferay.jethr0.build.Build;
import com.liferay.jethr0.entity.Entity;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public interface BuildRun extends Entity {

	public Build getBuild();

	public long getBuildId();

	public URL getBuildURL();

	public long getDuration();

	public JSONObject getInvokeJSONObject();

	public Result getResult();

	public State getState();

	public boolean isBlocked();

	public void setBuild(Build build);

	public void setBuildURL(URL buildURL);

	public void setDuration(long duration);

	public void setResult(Result result);

	public void setState(State state);

	public enum Result {

		FAILED("failed"), PASSED("passed");

		public static Result get(JSONObject jsonObject) {
			return getByKey(jsonObject.getString("key"));
		}

		public static Result getByKey(String key) {
			return _results.get(key);
		}

		public JSONObject getJSONObject() {
			return new JSONObject("{\"key\": \"" + getKey() + "\"}");
		}

		public String getKey() {
			return _key;
		}

		private Result(String key) {
			_key = key;
		}

		private static final Map<String, Result> _results = new HashMap<>();

		static {
			for (Result result : values()) {
				_results.put(result.getKey(), result);
			}
		}

		private final String _key;

	}

	public enum State {

		BLOCKED("blocked"), COMPLETED("completed"), OPENED("opened"),
		QUEUED("queued"), RUNNING("running");

		public static State get(JSONObject jsonObject) {
			return getByKey(jsonObject.getString("key"));
		}

		public static State getByKey(String key) {
			return _states.get(key);
		}

		public JSONObject getJSONObject() {
			return new JSONObject("{\"key\": \"" + getKey() + "\"}");
		}

		public String getKey() {
			return _key;
		}

		private State(String key) {
			_key = key;
		}

		private static final Map<String, State> _states = new HashMap<>();

		static {
			for (State state : values()) {
				_states.put(state.getKey(), state);
			}
		}

		private final String _key;

	}

}