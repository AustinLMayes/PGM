package tc.oc.pgm.util.tablist;

import javax.annotation.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * The pre-1.8 tab list is severely limited. This is essentially a no-op {@link TabView} for pre-1.8
 * clients.
 */
public class LegacyTabView extends TabView implements Listener {
  // The single player seeing this view
  private final Player viewer;

  protected @Nullable TabManager manager;

  public LegacyTabView(Player viewer) {
    super(viewer);
    this.viewer = viewer;
  }

  private void assertEnabled() {
    if (manager == null)
      throw new IllegalStateException(getClass().getSimpleName() + " is not enabled");
  }

  public Player getViewer() {
    return viewer;
  }

  public int getWidth() {
    return 3;
  }

  public int getHeight() {
    return 20;
  }

  public int getSize() {
    return getWidth() * getHeight();
  }

  /** Take control of the viewer's player list */
  public void enable(TabManager manager) {
    if (this.manager != null) disable();
    this.manager = manager;
    this.assertEnabled();
  }

  /** Tear down the display and return control the the viewer's player list to settings */
  public void disable() {
    if (this.manager != null) {
      this.manager.removeView(this);
      this.manager = null;
    }
  }

  @Override
  public void render() {}
}
