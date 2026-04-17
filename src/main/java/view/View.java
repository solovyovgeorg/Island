package view;

import statistic.Snapshot;

public interface View {
    public void getSnapshotInfo(Snapshot snapshot, int tick);

    void setMessage(String message);
    void getMessages();
}
