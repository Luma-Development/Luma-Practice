package best.luma.practice.service.profile.database;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import best.luma.practice.service.profile.Profile;
import org.bson.Document;

public class ProfileDB implements DB {

    @Override
    public void save(Profile profile) {
        Document document = new Document();

        document.put("uuid", profile.getUuid());

        if(profile.isOnline()) document.put("name", profile.getPlayer().getName());
        else document.put("name", profile.getName());

        Profile.getCollection().replaceOne(Filters.eq("uuid", profile.getUuid().toString()), document, new ReplaceOptions().upsert(true));
    }

    @Override
    public void load(Profile profile) {

        if(profile.getUuid() == null) return;

        if(Profile.getCollection() == null) return;

        Document document = Profile.getCollection().find(Filters.eq("uuid", profile.getUuid().toString())).first();

        if (document == null) {
            this.save(profile);
            return;
        }

        if (document.containsKey("name")) profile.setName(document.getString("name"));
    }
}
