/*
 * Copyright (c) 2005-2008 Vincent Vandenschrick. All rights reserved.
 */
package com.d2s.framework.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements a hierachical structure for holding action maps. An
 * action map is a map of action sets keyed by their grouping goal (like 'EDIT',
 * 'VIEW', ...).
 * <p>
 * Copyright (c) 2005-2008 Vincent Vandenschrick. All rights reserved.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class ActionMap {

  private List<ActionList> actionLists;
  private List<ActionMap>  parentActionMaps;

  private static void completeActionMap(
      Map<String, ActionList> bufferActionMap, List<ActionList> actionLists) {
    if (actionLists != null) {
      Map<String, ActionList> mapOfActionLists = new HashMap<String, ActionList>();
      for (ActionList al : actionLists) {
        mapOfActionLists.put(al.getName(), al);
      }
      for (Map.Entry<String, ActionList> actionListEntry : mapOfActionLists
          .entrySet()) {
        ActionList bufferActionList = bufferActionMap.get(actionListEntry
            .getKey());
        if (bufferActionList == null) {
          bufferActionList = actionListEntry.getValue().clone();
          bufferActionMap.put(actionListEntry.getKey(), bufferActionList);
        } else {
          for (IDisplayableAction localAction : actionListEntry.getValue()
              .getActions()) {
            int existingIndex = bufferActionList.getActions().indexOf(
                localAction);
            if (existingIndex >= 0) {
              bufferActionList.getActions().set(existingIndex, localAction);
            } else {
              bufferActionList.getActions().add(localAction);
            }
          }
        }
      }
    }
  }

  /**
   * Gets the list of action sets composing the parent action maps with the
   * local one.
   * 
   * @return the actions list.
   */
  public List<ActionList> getActionLists() {
    Map<String, ActionList> buffer = new LinkedHashMap<String, ActionList>();
    if (parentActionMaps != null) {
      for (ActionMap parentActionMap : parentActionMaps) {
        completeActionMap(buffer, parentActionMap.getActionLists());
      }
    }
    if (actionLists != null) {
      completeActionMap(buffer, actionLists);
    }
    return new ArrayList<ActionList>(buffer.values());
  }

  /**
   * Sets the action lists list.
   * 
   * @param actionLists
   *            the action lists list to set.
   */
  public void setActionLists(List<ActionList> actionLists) {
    this.actionLists = actionLists;
  }

  /**
   * Sets the parentActionMaps.
   * 
   * @param parentActionMaps
   *            the parentActionMaps to set.
   */
  public void setParentActionMaps(List<ActionMap> parentActionMaps) {
    this.parentActionMaps = parentActionMaps;
  }
}
