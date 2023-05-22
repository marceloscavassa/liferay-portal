import ClayAlert from '@clayui/alert';
import ClayButton from '@clayui/button';
import ClayModal, { useModal } from '@clayui/modal';
import ClayForm, { ClayInput, ClayCheckbox } from '@clayui/form';
import { useEffect, useState } from 'react';
import { Liferay } from '../../liferay/liferay';

interface InviteMemberModalProps {
  handleClose: () => void;
  selectedAccount: Account;
}

export function InviteMemberModal({
  handleClose,
  selectedAccount,
}: InviteMemberModalProps) {
  const { observer, onClose } = useModal({
    onClose: () => handleClose(),
  });

  const [email, setEmail] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [checkboxRoles, setCheckboxRoles] = useState<
    { roleName: String; isChecked: boolean }[]
  >([]);
  const [newUser, setNewUser] = useState<any>();
  const [accountRoles, setAccountRoles] = useState<any>();

  const listOfRoles = ['Account Administrator', 'App Editor'];

  useEffect(() => {
    const mapRoles = listOfRoles.map((x) => {
      return { roleName: x, isChecked: false };
    });
    setCheckboxRoles(mapRoles);
    getAccoutRoles();
  }, []);

  const jsonBody = () => {
    const invitedMemberRole = accountRoles.find((x: any) => {
      if (x.name === 'Invited Member') {
        return x;
      }
    });
    console.log(invitedMemberRole);
    return {
      alternateName: email.replace('@', '-'),
      emailAddress: email,
      familyName: lastName,
      givenName: firstName,
    };
  };

  const getAccoutRoles = async () => {
    const accountRoles = await fetch(
      `/o/headless-admin-user/v1.0/accounts/${selectedAccount.id}/account-roles`,
      {
        headers: {
          accept: 'application/json',
          'x-csrf-token': Liferay.authToken,
        },
      }
    );
    if (accountRoles.ok) {
      const data = await accountRoles.json();
      setAccountRoles(data.items);
    }
  };

  async function createNewUserIntoAccount() {
    try {
      const response = await fetch(
        `/o/headless-admin-user/v1.0/accounts/${selectedAccount.id}/user-accounts`,
        {
          headers: {
            accept: 'application/json',
            'Content-Type': 'application/json',
            'x-csrf-token': Liferay.authToken,
          },
          method: 'POST',
          body: JSON.stringify(jsonBody()),
        }
      );
    } catch (error) {
      <ClayAlert.ToastContainer>
        <ClayAlert
          autoClose={5000}
          displayType="danger"
          title="error"
        ></ClayAlert>
      </ClayAlert.ToastContainer>;
    }
  }

  async function addExistentUserIntoAccount() {
    try {
      const response = await fetch(
        `/o/headless-admin-user/v1.0/accounts/${selectedAccount.id}/user-accounts/by-email-address/${email}`,
        {
          headers: {
            accept: 'application/json',
            'x-csrf-token': Liferay.authToken,
          },
          method: 'POST',
          body: JSON.stringify(jsonBody),
        }
      );
    } catch (error) {
      <ClayAlert.ToastContainer>
        <ClayAlert
          autoClose={5000}
          displayType="danger"
          title="error"
        ></ClayAlert>
      </ClayAlert.ToastContainer>;
    }
  }

  const getUserByEmail = async (userEmail: String) => {
    try {
      const responseFilteredUserList = await fetch(
        `/o/headless-admin-user/v1.0/user-accounts?filter=emailAddress eq '${userEmail}'`,
        {
          headers: {
            accept: 'application/json',
            'x-csrf-token': Liferay.authToken,
          },
        }
      );

      if (responseFilteredUserList.ok) {
        const data = await responseFilteredUserList.json();
        if (data.items.length > 0) {
          console.log('getUserByEmail: ' + data);
          return data.items[0];
        }
      }
    } catch (error) {
      <ClayAlert.ToastContainer>
        <ClayAlert
          autoClose={5000}
          displayType="danger"
          title="error"
        ></ClayAlert>
      </ClayAlert.ToastContainer>;
    }
  };

  const addAccountRolesToUser = async (user : any) => {
    for (const checkboxRole of checkboxRoles) {
      if (checkboxRole.isChecked) {
        const matchingAccountRole = accountRoles.find(
          (accountRole: any) => accountRole.name == "Invited Member"
        );
        if (matchingAccountRole) {
          await callRolesApi(matchingAccountRole.id, user.id);
        }
      }
    }
  };
  const callRolesApi = async (roleId: String, userId: String) => {
    const response = await fetch(
      `/o/headless-admin-user/v1.0/accounts/${selectedAccount.id}/account-roles/${roleId}/user-accounts/${userId}`,
      {
        headers: {
          accept: 'application/json',
          'Content-Type': 'application/json',
          'x-csrf-token': Liferay.authToken,
        },
        method: 'POST',
      }
    );
    if (response.ok) {
      return;
    }
  };

  const handleSubmit = async (event: any) => {
    let user = '';
    event.preventDefault();
    if (!document.querySelector(':checked')) {
      console.log('noneChecked');
    } else {
      user = await getUserByEmail(email);
      setNewUser(user);
      console.log(user);
      if (!user) {
        console.log('after get User: ' + newUser);
        await createNewUserIntoAccount();
      } else {
        console.log('after get User: ' + newUser);
        await addExistentUserIntoAccount();
      }
      user = await getUserByEmail(email);
      console.log("pos-method user" + user)
      await addAccountRolesToUser(user);
      setTimeout(() => location.reload(), 200);
    }
  };

  const handleCheck = (selectedRoleName: String) => {
    const rolesChecked = checkboxRoles.map((role, index) => {
      if (selectedRoleName === role.roleName) {
        role.isChecked = !role.isChecked;
        return role;
      }
      return role;
    }, []);
    setCheckboxRoles(rolesChecked);
  };

  return (
    <ClayModal observer={observer} size="lg">
      <ClayModal.Header>Invite New Member</ClayModal.Header>

      <ClayModal.Body>
        <ClayForm onSubmit={handleSubmit}>
          <ClayForm.Group>
            <div>
              <ClayModal.TitleSection>
                <ClayModal.Title>Invite</ClayModal.Title>
              </ClayModal.TitleSection>
              <hr className="solid"></hr>
            </div>

            <div className="d-flex justify-content-between pb-5">
              <div className="pr-3 w-50">
                <label className="pb-1" htmlFor="firstName">
                  First Name
                </label>
                <ClayInput
                  id="firstName"
                  type="text"
                  required={true}
                  onChange={(event) => {
                    setFirstName(event.target.value);
                  }}
                />
              </div>
              <div className="pl-3 w-50">
                <label className="pb-1" htmlFor="lastName">
                  Last Name
                </label>
                <ClayInput
                  id="lastName"
                  type="text"
                  required={true}
                  onChange={(event) => {
                    setLastName(event.target.value);
                  }}
                />
              </div>
            </div>
            <label className="pb-1" htmlFor="emailAddress">
              Email
            </label>
            <ClayInput
              id="emailAddress"
              type="text"
              required={true}
              onChange={(event) => {
                setEmail(event.target.value);
              }}
            />
          </ClayForm.Group>

          <ClayForm.Group>
            <div className="pt-4">
              <ClayModal.TitleSection>
                <ClayModal.Title>Role</ClayModal.Title>
              </ClayModal.TitleSection>
              <hr className="solid"></hr>
            </div>
            <div>
              {listOfRoles.map((role, index) => {
                return (
                  <ClayCheckbox
                    label={role}
                    checked={checkboxRoles[index]?.isChecked}
                    value={role}
                    onChange={() => handleCheck(role)}
                  />
                );
              })}
            </div>
          </ClayForm.Group>
          <ClayButton.Group
            className="d-flex justify-content-between justify-content-lg-end modal-footer"
            spaced
          >
            <ClayButton
              className="cancelButton"
              outline={true}
              type="button"
              onClick={() => onClose()}
            >
              Cancel
            </ClayButton>
            <ClayButton type="submit">Send Invite</ClayButton>
          </ClayButton.Group>
        </ClayForm>
      </ClayModal.Body>
    </ClayModal>
  );
}
