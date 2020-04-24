package tc.oc.pgm.tablist;

import java.util.Collection;
import java.util.stream.Collectors;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import tc.oc.pgm.api.map.Contributor;
import tc.oc.pgm.api.match.Match;
import tc.oc.pgm.util.component.Component;
import tc.oc.pgm.util.component.types.PersonalizedText;
import tc.oc.pgm.util.component.types.PersonalizedTranslatable;
import tc.oc.pgm.util.named.NameStyle;
import tc.oc.pgm.util.tablist.DynamicTabEntry;
import tc.oc.pgm.util.tablist.TabView;
import tc.oc.pgm.util.translations.TranslationUtils;

public class MapTabEntry extends DynamicTabEntry {

  private final Match match;

  protected MapTabEntry(Match match) {
    this.match = match;
  }

  @Override
  public BaseComponent getContent(TabView view) {
    Component content =
        new PersonalizedText(match.getMap().getName(), ChatColor.AQUA, ChatColor.BOLD);

    Collection<Contributor> authors = match.getMap().getAuthors();
    if (!authors.isEmpty()) {
      content =
          new PersonalizedText(
              new PersonalizedTranslatable(
                  "misc.authorship",
                  content,
                  TranslationUtils.combineComponents(
                      authors.stream()
                          .map(contributor -> contributor.getStyledName(NameStyle.FANCY))
                          .collect(Collectors.toList()))),
              ChatColor.DARK_GRAY);
    }

    return content.render(view.getViewer());
  }
}
