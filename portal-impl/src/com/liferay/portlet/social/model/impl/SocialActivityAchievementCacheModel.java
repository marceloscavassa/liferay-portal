/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.social.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.social.kernel.model.SocialActivityAchievement;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SocialActivityAchievement in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SocialActivityAchievementCacheModel
	implements CacheModel<SocialActivityAchievement>, Externalizable,
			   MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SocialActivityAchievementCacheModel)) {
			return false;
		}

		SocialActivityAchievementCacheModel
			socialActivityAchievementCacheModel =
				(SocialActivityAchievementCacheModel)object;

		if ((activityAchievementId ==
				socialActivityAchievementCacheModel.activityAchievementId) &&
			(mvccVersion == socialActivityAchievementCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, activityAchievementId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", ctCollectionId=");
		sb.append(ctCollectionId);
		sb.append(", activityAchievementId=");
		sb.append(activityAchievementId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", firstInGroup=");
		sb.append(firstInGroup);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SocialActivityAchievement toEntityModel() {
		SocialActivityAchievementImpl socialActivityAchievementImpl =
			new SocialActivityAchievementImpl();

		socialActivityAchievementImpl.setMvccVersion(mvccVersion);
		socialActivityAchievementImpl.setCtCollectionId(ctCollectionId);
		socialActivityAchievementImpl.setActivityAchievementId(
			activityAchievementId);
		socialActivityAchievementImpl.setGroupId(groupId);
		socialActivityAchievementImpl.setCompanyId(companyId);
		socialActivityAchievementImpl.setUserId(userId);
		socialActivityAchievementImpl.setCreateDate(createDate);

		if (name == null) {
			socialActivityAchievementImpl.setName("");
		}
		else {
			socialActivityAchievementImpl.setName(name);
		}

		socialActivityAchievementImpl.setFirstInGroup(firstInGroup);

		socialActivityAchievementImpl.resetOriginalValues();

		return socialActivityAchievementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		ctCollectionId = objectInput.readLong();

		activityAchievementId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();

		createDate = objectInput.readLong();
		name = objectInput.readUTF();

		firstInGroup = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(ctCollectionId);

		objectOutput.writeLong(activityAchievementId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(createDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeBoolean(firstInGroup);
	}

	public long mvccVersion;
	public long ctCollectionId;
	public long activityAchievementId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public String name;
	public boolean firstInGroup;

}