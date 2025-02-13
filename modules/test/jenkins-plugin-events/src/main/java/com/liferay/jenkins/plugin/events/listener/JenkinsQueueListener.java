/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.plugin.events.listener;

import com.liferay.jenkins.plugin.events.publisher.JenkinsPublisher;
import com.liferay.jenkins.plugin.events.publisher.JenkinsPublisherUtil;

import hudson.Extension;

import hudson.model.Queue;
import hudson.model.queue.QueueListener;

/**
 * @author Michael Hashimoto
 */
@Extension
public class JenkinsQueueListener extends QueueListener {

	@Override
	public void onEnterBlocked(Queue.BlockedItem blockedItem) {
		JenkinsPublisherUtil.publish(
			JenkinsPublisher.EventTrigger.QUEUE_ITEM_ENTER_BLOCKED,
			blockedItem);
	}

	@Override
	public void onEnterBuildable(Queue.BuildableItem buildableItem) {
		JenkinsPublisherUtil.publish(
			JenkinsPublisher.EventTrigger.QUEUE_ITEM_ENTER_BUILDABLE,
			buildableItem);
	}

	@Override
	public void onEnterWaiting(Queue.WaitingItem waitingItem) {
		JenkinsPublisherUtil.publish(
			JenkinsPublisher.EventTrigger.QUEUE_ITEM_ENTER_WAITING,
			waitingItem);
	}

	@Override
	public void onLeaveBlocked(Queue.BlockedItem blockedItem) {
		JenkinsPublisherUtil.publish(
			JenkinsPublisher.EventTrigger.QUEUE_ITEM_LEAVE_BLOCKED,
			blockedItem);
	}

	@Override
	public void onLeaveBuildable(Queue.BuildableItem buildableItem) {
		JenkinsPublisherUtil.publish(
			JenkinsPublisher.EventTrigger.QUEUE_ITEM_LEAVE_BUILDABLE,
			buildableItem);
	}

	@Override
	public void onLeaveWaiting(Queue.WaitingItem waitingItem) {
		JenkinsPublisherUtil.publish(
			JenkinsPublisher.EventTrigger.QUEUE_ITEM_LEAVE_WAITING,
			waitingItem);
	}

	@Override
	public void onLeft(Queue.LeftItem leftItem) {
		JenkinsPublisherUtil.publish(
			JenkinsPublisher.EventTrigger.QUEUE_ITEM_LEFT, leftItem);
	}

}