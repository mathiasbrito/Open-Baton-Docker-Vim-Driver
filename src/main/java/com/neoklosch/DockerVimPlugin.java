package com.neoklosch;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import org.openbaton.catalogue.mano.common.DeploymentFlavour;
import org.openbaton.catalogue.nfvo.*;
import org.openbaton.catalogue.nfvo.Network;
import org.openbaton.catalogue.security.Key;
import org.openbaton.exceptions.VimDriverException;
import org.openbaton.plugin.PluginStarter;
import org.openbaton.vim.drivers.interfaces.VimDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeoutException;

/*
 * Copyright (c) 2017 Neoklosch (Markus Paeschke)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class DockerVimPlugin extends VimDriver {
  private static final Logger logger = LoggerFactory.getLogger(DockerVimPlugin.class);

  private DockerClient dockerClient;

  protected void createDockerInstance() {
    try {
      dockerClient = DefaultDockerClient.fromEnv().build();
    } catch (DockerCertificateException dce) {
      logger.debug(dce.getMessage());
    }
  }

  public static void main(String[] args)
      throws NoSuchMethodException, IOException, InstantiationException, TimeoutException,
          IllegalAccessException, InvocationTargetException {
    if (args.length <= 1) {
      PluginStarter.registerPlugin(DockerVimPlugin.class, "docker", "localhost", 5672, 10);
    } else {
      PluginStarter.registerPlugin(
          DockerVimPlugin.class,
          args[0],
          args[1],
          Integer.parseInt(args[2]),
          Integer.parseInt(args[3]));
    }
  }

  public Server launchInstance(
      VimInstance vimInstance,
      String name,
      String image,
      String flavor,
      String keypair,
      Set<String> network,
      Set<String> secGroup,
      String userData)
      throws VimDriverException {

    createDockerInstance();

    return null;
  }

  public List<Server> listServer(VimInstance vimInstance) throws VimDriverException {
    List<Server> servers = new ArrayList<Server>();

    Server server = new Server();
    server.setExtId("ext_id");
    server.setName("server_name0");
    DeploymentFlavour flavour = new DeploymentFlavour();
    flavour.setRam(10);
    flavour.setVcpus(1);
    server.setFlavor(flavour);
    server.setIps(new HashMap<String, List<String>>());
    servers.add(server);

    return servers;
  }

  public List<Network> listNetworks(VimInstance vimInstance) throws VimDriverException {
    createDockerInstance();

    //    List<com.spotify.docker.client.messages.Network> networks;
    //    List<Network> nfvNetworks = new ArrayList<Network>();
    //    try {
    //      networks = dockerClient.listNetworks();
    //    } catch (DockerException de) {
    //      logger.debug(de.getMessage());
    //      throw new VimDriverException(de.getMessage());
    //    } catch (InterruptedException ie) {
    //      logger.debug(ie.getMessage());
    //      throw new VimDriverException(ie.getMessage());
    //    }
    //
    //    if (networks != null) {
    //      for (com.spotify.docker.client.messages.Network dockerNetwork : networks) {
    //        if (!dockerNetwork.driver().equals("bridge")) {
    //          continue;
    //        }
    //        Network openBatonNetwork = new Network();
    //        openBatonNetwork.setId(dockerNetwork.id());
    //        openBatonNetwork.setName(dockerNetwork.name());
    //        Boolean isInternal = dockerNetwork.internal();
    //        openBatonNetwork.setExternal(isInternal != null && !isInternal);
    //        nfvNetworks.add(openBatonNetwork);
    //      }
    //    }
    //    return nfvNetworks;

    List<Network> networks = new ArrayList<Network>();
    Network network = new Network();
    network.setExtId("network-id");
    network.setName("docker network");
    network.setExternal(true);
    networks.add(network);

    return networks;
  }

  public Network createNetwork(VimInstance vimInstance, Network network) throws VimDriverException {
    createDockerInstance();

    //    NetworkConfig.Builder networkConfigBuilder = NetworkConfig.builder();
    //    networkConfigBuilder =
    //        networkConfigBuilder.ipam(Ipam.create("default", Collections.<IpamConfig>emptyList()));
    //    final NetworkConfig bridgeDriverConfig =
    //        networkConfigBuilder.name(network.getName()).driver("bridge").build();
    //    try {
    //      final NetworkCreation bridgeDriverCreation = dockerClient.createNetwork(bridgeDriverConfig);
    //      network.setExtId(bridgeDriverCreation.id());
    //      network.setExternal(true);
    //    } catch (DockerException de) {
    //      logger.debug(de.getMessage());
    //      throw new VimDriverException(de.getMessage());
    //    } catch (InterruptedException ie) {
    //      logger.debug(ie.getMessage());
    //      throw new VimDriverException(ie.getMessage());
    //    }
    //    return network;
    return network;
  }

  public Network updateNetwork(VimInstance vimInstance, Network network) throws VimDriverException {
    return network;
  }

  public boolean deleteNetwork(VimInstance vimInstance, String extId) throws VimDriverException {
    createDockerInstance();

    //    Network networkToRemove = getNetworkById(vimInstance, extId);
    //
    //    if (networkToRemove != null) {
    //      try {
    //        dockerClient.removeNetwork(extId);
    //      } catch (DockerException de) {
    //        logger.debug(de.getMessage());
    //        throw new VimDriverException(de.getMessage());
    //      } catch (InterruptedException ie) {
    //        logger.debug(ie.getMessage());
    //        throw new VimDriverException(ie.getMessage());
    //      }
    //    }
    //
    //    return networkToRemove != null;
    return true;
  }

  public Network getNetworkById(VimInstance vimInstance, String id) throws VimDriverException {
    createDockerInstance();

    //    Network networkToRemove = null;
    //    List<Network> networks = listNetworks(vimInstance);
    //
    //    if (networks != null) {
    //      for (Network network : networks) {
    //        if (network.getId().equals(id)) {
    //          networkToRemove = network;
    //          break;
    //        }
    //      }
    //    }
    //
    //    return networkToRemove;
    Network network = new Network();
    network.setExtId("id");
    network.setName("new created docker network");
    network.setExternal(true);
    return network;
  }

  public Server launchInstanceAndWait(
      VimInstance vimInstance,
      String hostname,
      String image,
      String extId,
      String keyPair,
      Set<String> networks,
      Set<String> securityGroups,
      String s,
      Map<String, String> floatingIps,
      Set<Key> keys)
      throws VimDriverException {
    return launchInstanceAndWait(
        vimInstance, hostname, image, extId, keyPair, networks, securityGroups, s);
  }

  public Server launchInstanceAndWait(
      VimInstance vimInstance,
      String hostname,
      String image,
      String extId,
      String keyPair,
      Set<String> networks,
      Set<String> securityGroups,
      String s)
      throws VimDriverException {
    Server server = new Server();
    server.setExtId("docker_server_ext_id");
    server.setName("server_new_created_docker");
    DeploymentFlavour flavour = new DeploymentFlavour();
    flavour.setRam(10);
    flavour.setVcpus(1);
    server.setFlavor(flavour);
    server.setIps(new HashMap<String, List<String>>());
    return server;
  }

  public void deleteServerByIdAndWait(VimInstance vimInstance, String id)
      throws VimDriverException {}

  public List<NFVImage> listImages(VimInstance vimInstance) throws VimDriverException {
    createDockerInstance();
    List<NFVImage> images = new ArrayList<NFVImage>();
    List<Image> dockerImages = null;
    try {
      dockerImages = dockerClient.listImages();
    } catch (DockerException de) {
      logger.debug(de.getMessage());
    } catch (InterruptedException ie) {
      logger.debug(ie.getMessage());
    }
    if (dockerImages != null) {
      for (Image image : dockerImages) {
        NFVImage nfvImage = new NFVImage();
        nfvImage.setExtId(image.id());
        nfvImage.setName(image.id());
        nfvImage.setContainerFormat("docker");
        nfvImage.setCreated(new Date());
        nfvImage.setIsPublic(true);
        images.add(nfvImage);
        logger.debug(image.id());
      }
    }

    return images;

    //    List<NFVImage> images = new ArrayList<NFVImage>();
    //    for (int index = 0; index < 10; index++) {
    //      NFVImage nfvImage = new NFVImage();
    //      nfvImage.setName("ubuntu-14.04-server-cloudimg-amd64-disk1");
    //      nfvImage.setContainerFormat("docker");
    //      nfvImage.setCreated(new Date());
    //      nfvImage.setIsPublic(true);
    //      images.add(nfvImage);
    //    }
    //
    //    return images;
  }

  public NFVImage addImage(VimInstance vimInstance, NFVImage image, byte[] imageFile)
      throws VimDriverException {
    return image;
  }

  public NFVImage addImage(VimInstance vimInstance, NFVImage image, String image_url)
      throws VimDriverException {
    return image;
  }

  public NFVImage updateImage(VimInstance vimInstance, NFVImage image) throws VimDriverException {
    return image;
  }

  public NFVImage copyImage(VimInstance vimInstance, NFVImage image, byte[] imageFile)
      throws VimDriverException {
    return image;
  }

  public boolean deleteImage(VimInstance vimInstance, NFVImage image) throws VimDriverException {
    createDockerInstance();
    //    boolean removedImages = false;
    //    try {
    //      removedImages = dockerClient.removeImage(image.getName()).size() > 0;
    //    } catch (DockerException de) {
    //      logger.debug(de.getMessage());
    //    } catch (InterruptedException ie) {
    //      logger.debug(ie.getMessage());
    //    }
    //    return removedImages;
    return true;
  }

  public Subnet createSubnet(VimInstance vimInstance, Network createdNetwork, Subnet subnet)
      throws VimDriverException {
    return subnet;
  }

  public Subnet updateSubnet(VimInstance vimInstance, Network updatedNetwork, Subnet subnet)
      throws VimDriverException {
    return subnet;
  }

  public List<String> getSubnetsExtIds(VimInstance vimInstance, String network_extId)
      throws VimDriverException {
    List<String> strings = new ArrayList<String>();
    strings.add("one");
    strings.add("two");
    strings.add("three");
    return strings;
  }

  public boolean deleteSubnet(VimInstance vimInstance, String existingSubnetExtId)
      throws VimDriverException {
    return true;
  }

  public Quota getQuota(VimInstance vimInstance) throws VimDriverException {
    Quota quota = new Quota();
    quota.setCores(99999);
    quota.setFloatingIps(99999);
    quota.setInstances(99999);
    quota.setKeyPairs(99999);
    quota.setRam(99999);
    quota.setTenant("docker-test-tenant");
    return quota;
  }

  public String getType(VimInstance vimInstance) throws VimDriverException {
    return "docker";
  }

  public List<DeploymentFlavour> listFlavors(VimInstance vimInstance) throws VimDriverException {
    // Docker does not support flavors

    List<DeploymentFlavour> list = new ArrayList<DeploymentFlavour>();

    for (int index = 0; index < 10; index++) {
      DeploymentFlavour flavour = new DeploymentFlavour();
      flavour.setExtId("ext_id_" + index);
      flavour.setFlavour_key("m1.nano." + index);
      list.add(flavour);
    }

    return list;
  }

  public DeploymentFlavour addFlavor(VimInstance vimInstance, DeploymentFlavour deploymentFlavour)
      throws VimDriverException {
    // Docker does not support flavors
    return deploymentFlavour;
  }

  public DeploymentFlavour updateFlavor(
      VimInstance vimInstance, DeploymentFlavour deploymentFlavour) throws VimDriverException {
    // Docker does not support flavors
    return deploymentFlavour;
  }

  public boolean deleteFlavor(VimInstance vimInstance, String extId) throws VimDriverException {
    // Docker does not support flavors
    return false;
  }
}
