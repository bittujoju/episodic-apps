create table episodes (
  id bigint not null auto_increment,
  show_id bigint not null,
  season_number integer not null,
  episode_number integer not null,
  primary key (id),
  index episode_shows_ind (show_id),
  foreign key (show_id)
  references shows(id)
    on delete cascade
);