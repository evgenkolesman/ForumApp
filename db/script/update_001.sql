CREATE Table if not exists authorities(
                                          id int primary key,
                                          aurhotrity text
);
CREATE Table users(
                      id int primary key,
                      name text,
                      password text,
                      authority_id int references authorities(id),
                      enabled boolean
);

CREATE Table posts(
                      id int primary key,
                      name text,
                      description text,
                      created timestamp,
                      user_id  int references users(id)
);
