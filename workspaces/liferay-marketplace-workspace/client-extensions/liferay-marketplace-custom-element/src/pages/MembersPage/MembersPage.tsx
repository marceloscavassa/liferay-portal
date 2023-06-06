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

import ClayAlert, { DisplayType } from '@clayui/alert';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import { useCallback, useEffect, useState } from 'react';

import { DashboardMemberTableRow } from '../../components/DashboardTable/DashboardMemberTableRow';
import {
  DashboardTable,
  TableHeaders,
} from '../../components/DashboardTable/DashboardTable';
import { InviteMemberModal } from '../../components/InviteMemberModal/InviteMemberModal';
import { MemberProfile } from '../../components/MemberProfile/MemberProfile';
import { getMyUserAccount, getUserAccounts } from '../../utils/api';
import {
  DashboardListItems,
  DashboardPage,
} from '../DashBoardPage/DashboardPage';
import {
  AccountBriefProps,
  MemberProps,
  UserAccountProps,
  adminRoles,
  customerRoles,
  publisherRoles,
} from '../PublishedAppsDashboardPage/PublishedDashboardPageUtil';

interface MembersPageProps {
  dashboardNavigationItems: DashboardListItems[];
  icon: string;
  listOfRoles: string[];
  rolesPermissionDescription: PermissionDescription[];
  selectedAccount: Account;
  setShowDashboardNavigation?: (value: boolean) => void;
}

const memberTableHeaders: TableHeaders = [
  {
    iconSymbol: 'order-arrow',
    title: 'Name',
  },
  {
    title: 'Email',
  },
  {
    title: 'Role',
  },
];

const memberMessages = {
  description: 'Manage users in your development team and invite new ones',
  emptyStateMessage: {
    description1: 'Create new members and they will show up here.',
    description2: 'Click on “New Member” to start.',
    title: 'No Members Yet',
  },
  title: 'Members',
};

export function MembersPage({
  dashboardNavigationItems,
  icon,
  listOfRoles,
  rolesPermissionDescription,
  selectedAccount,
}: MembersPageProps) {
  const [visible, setVisible] = useState<boolean>(false);
  const [loading] = useState<boolean>(false);
  const [members, setMembers] = useState<MemberProps[]>(Array<MemberProps>());
  const [selectedMember, setSelectedMember] = useState<MemberProps>();
  const [isCurrentUserAdmin, setIsCurrentUserAdmin] = useState<boolean>(false);
  const [toastItems, setToastItems] = useState<
    { message: string; title?: string; type: DisplayType }[]
  >([]);

  const renderToast = (message: string, title: string, type: DisplayType) => {
    setToastItems([...toastItems, { message, title, type }]);
  };

  const memberMessages = {
    description: 'Manage users in your development team and invite new ones',
    emptyStateMessage: {
      description1: 'Create new members and they will show up here.',
      description2: 'Click on “New Member” to start.',
      title: 'No Members Yet',
    },
    title: 'Members',
  };

  function getRolesList(accountBriefs: AccountBrief[]) {
    const rolesList: string[] = [];

    const accountBrief = accountBriefs.find(
      (accountBrief) => accountBrief.id === selectedAccount.id
    );

    accountBrief?.roleBriefs.forEach((role: RoleBrief) => {
      rolesList.push(role.name);
    });

    return rolesList.join(', ');
  }

  useEffect(() => {
    (async () => {
      const currentUserAccountResponse = await getMyUserAccount();

      const currentUserAccount = {
        accountBriefs: currentUserAccountResponse.accountBriefs,
        isCustomerAccount: false,
        isPublisherAccount: false,
        isAdminAccount: false,
      };

      const currentUserAccountBriefs = currentUserAccount.accountBriefs.find(
        (accountBrief: { id: number }) => accountBrief.id === selectedAccount.id
      );

      if (currentUserAccountBriefs) {
        customerRoles.forEach((customerRole) => {
          if (
            currentUserAccountBriefs.roleBriefs.find(
              (role: { name: string }) => role.name === customerRole
            )
          ) {
            currentUserAccount.isCustomerAccount = true;
          }
        });

        publisherRoles.forEach((publisherRole) => {
          if (
            currentUserAccountBriefs.roleBriefs.find(
              (role: { name: string }) => role.name === publisherRole
            )
          ) {
            currentUserAccount.isPublisherAccount = true;
          }
        });

        adminRoles.forEach((adminRole) => {
          if (
            currentUserAccountBriefs.roleBriefs.find(
              (role: { name: string }) => role.name === adminRole
            )
          ) {
            currentUserAccount.isAdminAccount = true;
            setIsCurrentUserAdmin(true);
          }
        });
      }

      const accountsListResponse = await getUserAccounts();

      const membersList = accountsListResponse?.items.map(
        (member: UserAccountProps) => {
          return {
            accountBriefs: member.accountBriefs,
            dateCreated: member.dateCreated,
            email: member.emailAddress,
            image: member.image,
            isInvitedMember: false,
            isCustomerAccount: false,
            isPublisherAccount: false,
            lastLoginDate: member.lastLoginDate,
            name: member.name,
            role: getRolesList(member.accountBriefs),
            userId: member.id,
          } as MemberProps;
        }
      );

      membersList.forEach((member: MemberProps) => {
        const rolesList = member.role.split(', ');

        customerRoles.forEach((customerRole) => {
          if (rolesList.find((role) => role === customerRole)) {
            member.isCustomerAccount = true;
          }
        });

        publisherRoles.forEach((publisherRole) => {
          if (rolesList.find((role) => role === publisherRole)) {
            member.isPublisherAccount = true;
          }
        });

        if (rolesList.find((role) => role === 'Invited Member')) {
          member.isInvitedMember = true;
        }
      });

      let filteredMembersList: MemberProps[] = [];

      filteredMembersList = membersList.filter((member: MemberProps) => {
        return (
          member.accountBriefs.find(
            (accountBrief: AccountBriefProps) =>
              accountBrief.externalReferenceCode ===
              selectedAccount.externalReferenceCode
          ) &&
          (member.isPublisherAccount ||
            member.isCustomerAccount ||
            member.isInvitedMember)
        );
      });

      setMembers(filteredMembersList);
    })();
  }, [visible, selectedAccount]);

  return (
    <>
      {!loading ? (
        <DashboardPage
          buttonMessage={isCurrentUserAdmin ? '+ New Member' : ''}
          dashboardNavigationItems={dashboardNavigationItems}
          messages={memberMessages}
          onButtonClick={() => setVisible(true)}
        >
          {selectedMember ? (
            <MemberProfile
              member={selectedMember}
              setSelectedMember={setSelectedMember}
            ></MemberProfile>
          ) : (
            <>
              <DashboardTable<MemberProps>
                emptyStateMessage={memberMessages.emptyStateMessage}
                icon={icon}
                items={members}
                tableHeaders={memberTableHeaders}
              >
                {(member) => (
                  <DashboardMemberTableRow
                    item={member}
                    key={member.name}
                    onSelectedMemberChange={setSelectedMember}
                  />
                )}
              </DashboardTable>
            </>
          )}
        </DashboardPage>
      ) : (
        <ClayLoadingIndicator
          className="members-page-loading-indicator"
          displayType="primary"
          shape="circle"
          size="md"
        />
      )}

      {visible && (
        <InviteMemberModal
          handleClose={() => setVisible(false)}
          listOfRoles={listOfRoles}
          rolesPermissionDescription={rolesPermissionDescription}
          selectedAccount={selectedAccount}
        ></InviteMemberModal>
      )}
    </>
  );
}
