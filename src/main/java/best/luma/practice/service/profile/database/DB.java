package best.luma.practice.service.profile.database;

import best.luma.practice.service.profile.Profile;

public interface DB {

    void save(Profile profile);

    void load(Profile profile);
}
