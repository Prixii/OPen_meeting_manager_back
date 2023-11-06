CREATE SCHEMA ROOT;
CREATE TABLE account (
    id INTEGER PRIMARY KEY ,
    name VARCHAR(20),
    phone VARCHAR(20),
    password VARCHAR(255)
);

CREATE TABLE organization (
    id INTEGER PRIMARY KEY ,
    creator INTEGER,
    name VARCHAR(20),
    dissolved SMALLINT,
    CONSTRAINT account_creator_ref FOREIGN KEY (creator) REFERENCES account(id)
);


CREATE TABLE invitation (
    id INTEGER PRIMARY KEY ,
    organization INTEGER,
    account INTEGER,
    state VARCHAR(1),
    CONSTRAINT invitation_organization_ref FOREIGN KEY (organization) REFERENCES organization(id) ,
    CONSTRAINT invitation_account_ref FOREIGN KEY (account) REFERENCES account(id)
);

CREATE TABLE member (
    id INTEGER PRIMARY KEY,
    organization INTEGER,
    account INTEGER,
    CONSTRAINT member_organization_ref FOREIGN KEY (organization) REFERENCES organization(id) ,
    CONSTRAINT member_account_ref FOREIGN KEY (account) REFERENCES account(id)
);

CREATE TABLE meeting (
    id INTEGER PRIMARY KEY ,
    creator INTEGER,
    finished SMALLINT,
    canceled SMALLINT,
    startTime DATE,
    endTime DATE
    CONSTRAINT meeting_creator_ref FOREIGN KEY (creator) REFERENCES account(id),
);

CREATE TABLE participants (
    account INTEGER,
    meeting INTEGER,
    CONSTRAINT participants_account_ref FOREIGN KEY (account) REFERENCES account(id),
    CONSTRAINT participants_meeting_ref FOREIGN KEY (meeting) REFERENCES meeting(id)
);


