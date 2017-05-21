create table viewings (
  id bigint not null auto_increment,
  user_id bigint not null,
  show_id bigint not null,
  episode_id bigint not null,
  updated_at datetime,
  timecode int,
  primary key (id),
  index viewings_episode_ind (episode_id),
  foreign key (episode_id)
  references episodes(id)
    on delete cascade,
  index viewings_user_ind (user_id),
  foreign key (user_id)
  references users(id)
    on delete cascade,
  index viewings_show_ind (show_id),
  foreign key (show_id)
  references shows(id)
    on delete cascade
);